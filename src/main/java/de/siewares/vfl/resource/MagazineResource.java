package de.siewares.vfl.resource;

import de.siewares.vfl.entity.Document;
import de.siewares.vfl.entity.DocumentMetadata;
import de.siewares.vfl.entity.User;
import de.siewares.vfl.service.ArchiveService;
import de.siewares.vfl.service.UserService;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST web service for archive service {@link IArchiveService}.
 *
 * /archive/upload?file={file}&person={person}&date={date} Add a document POST
 * file: A file posted in a multipart request person: The name of the uploading
 * person date: The date of the document
 *
 * /archive/documents?person={person}&date={date} Find documents GET person:
 * The name of the uploading person date: The date of the document
 *
 * /archive/document/{id} Get a document GET id: The UUID of a document
 *
 * All service calls are delegated to instances of {@link IArchiveService}.
 *
 * @author Daniel Murygin <daniel.murygin[at]gmail[dot]com>
 */
@Controller
@RequestMapping(value = "/magazine")
public class MagazineResource {

  @Autowired
  @Qualifier("magazineService")
  ArchiveService magazineService;

  @Autowired
  private UserService userService;

  /**
   * Adds a document to the archive.
   *
   * Url: /archive/upload?file={file}&person={person}&date={date} [POST]
   *
   * @param file A file posted in a multipart request
   * @param person The name of the uploading person
   * @param date The date of the document
   * @return The meta data of the added document
   */
  @RequestMapping(value = "/upload", method = RequestMethod.POST)
  public @ResponseBody
  DocumentMetadata handleFileUpload(
          @RequestParam(value = "file", required = true) MultipartFile file,
          @RequestParam(value = "issue", required = true) Integer issue) {

    try {
      User user = userService.get();

      PDDocument pdf = PDDocument.load(file.getBytes());
      PDFRenderer pdfRenderer = new PDFRenderer(pdf);

      int w = 106;
      int h = 150;
      BufferedImage previewImg = pdfRenderer.renderImageWithDPI(0, 300, ImageType.RGB);
      Image tmp = previewImg.getScaledInstance(w, h, Image.SCALE_SMOOTH);
      BufferedImage resized = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2d = resized.createGraphics();
      g2d.drawImage(tmp, 0, 0, null);
      g2d.dispose();

      Document document = new Document(file.getBytes(), file.getOriginalFilename(), new Date(), user.toString(), issue, resized);
      getArchiveService().save(document);
      return document.getMetadata();
    } catch (RuntimeException e) {
      //LOG.error("Error while uploading.", e);
      throw e;
    } catch (Exception e) {
      // LOG.error("Error while uploading.", e);
      throw new RuntimeException(e);
    }
  }

  /**
   * Finds document in the archive. Returns a list of document meta data which
   * does not include the file data. Use getDocument to get the file. Returns an
   * empty list if no document was found.
   *
   * Url: /archive/documents?person={person}&date={date} [GET]
   *
   * @param person The name of the uploading person
   * @param date The date of the document
   * @return A list of document meta data
   */
  @RequestMapping(value = "/documents", method = RequestMethod.GET)
  public HttpEntity<List<DocumentMetadata>> findDocument(
          @RequestParam(value = "person", required = false) String person,
          @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
          @RequestParam(value = "issue", required = false) Integer issue) {
    HttpHeaders httpHeaders = new HttpHeaders();
    return new ResponseEntity<>(getArchiveService().findDocuments(person, date, issue), httpHeaders, HttpStatus.OK);
  }

  /**
   * Returns the document file from the archive with the given UUID.
   *
   * Url: /archive/document/{id} [GET]
   *
   * @param id The UUID of a document
   * @return The document file
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public HttpEntity<byte[]> getDocument(@PathVariable String id) {
    // send it back to the client
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PDF);
    return new ResponseEntity<byte[]>(getArchiveService().getDocumentFile(id), httpHeaders, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}/thumbnail", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
  public ResponseEntity< byte[]> getDocumentThumbnail(@PathVariable String id) {
    try {
      HttpHeaders httpHeaders = new HttpHeaders();
      httpHeaders.setContentType(MediaType.IMAGE_PNG);
      BufferedImage thumbnail = getArchiveService().getDocument(id).getThumbnail();
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ImageIO.write(thumbnail, "png", baos);
      baos.flush();
      byte[] imageInByte = baos.toByteArray();
      baos.close();

      return new ResponseEntity<>(imageInByte, httpHeaders, HttpStatus.OK);
    } catch (IOException ex) {
      Logger.getLogger(MagazineResource.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
  }

  public ArchiveService getArchiveService() {
    return magazineService;
  }

  public void setArchiveService(ArchiveService archiveService) {
    this.magazineService = archiveService;
  }

}
