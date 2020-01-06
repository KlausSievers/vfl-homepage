package de.siewares.vfl.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

/**
 * A document from an archive managed by {@link IArchiveService}.
 *
 * @author Daniel Murygin <daniel.murygin[at]gmail[dot]com>
 */
public class Document extends DocumentMetadata implements Serializable {

  private static final long serialVersionUID = 2004955454853853315L;

  private byte[] fileData;
  private BufferedImage thumbnail;

  public Document(byte[] fileData, String fileName, Date documentDate, String personName, Integer issue, BufferedImage previewImg) {
    super(fileName, documentDate, personName, issue);
    this.fileData = fileData;
    this.thumbnail = previewImg;
  }

  public Document(Properties properties) {
    super(properties);
  }

  public Document(DocumentMetadata metadata) {
    super(metadata.getUuid(), metadata.getFileName(), metadata.getDocumentDate(), metadata.getPersonName(), metadata.getIssue());
  }

  public byte[] getFileData() {
    return fileData;
  }

  public void setFileData(byte[] fileData) {
    this.fileData = fileData;
  }

  public BufferedImage getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(BufferedImage thumbnail) {
    this.thumbnail = thumbnail;
  }

  public DocumentMetadata getMetadata() {
    return new DocumentMetadata(getUuid(), getFileName(), getDocumentDate(), getPersonName(), getIssue());
  }

}
