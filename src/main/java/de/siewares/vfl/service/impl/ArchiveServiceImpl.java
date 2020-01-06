package de.siewares.vfl.service.impl;

import de.siewares.vfl.entity.Document;
import de.siewares.vfl.entity.DocumentMetadata;
import de.siewares.vfl.repository.IDocumentDao;
import de.siewares.vfl.service.ArchiveService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * A service to save, find and get documents from an archive.
 *
 * @author Daniel Murygin <daniel.murygin[at]gmail[dot]com>
 */
public class ArchiveServiceImpl implements ArchiveService, Serializable {

  private static final long serialVersionUID = 8119784722798361327L;

  private IDocumentDao documentDao;

  public ArchiveServiceImpl(IDocumentDao documentDao) {
    this.documentDao = documentDao;
  }
  

  /**
   * Saves a document in the archive.
   *
   * @see
   * org.murygin.archive.service.IArchiveService#save(org.murygin.archive.service.Document)
   */
  @Override
  public DocumentMetadata save(Document document) {
    getDocumentDao().insert(document);
    return document.getMetadata();
  }

  /**
   * Finds document in the archive
   *
   * @see
   * org.murygin.archive.service.IArchiveService#findDocuments(java.lang.String,
   * java.util.Date)
   */
  @Override
  public List<DocumentMetadata> findDocuments(String personName, Date date, Integer issue) {
    return getDocumentDao().findByPersonNameDate(personName, date);
  }

  /**
   * Returns the document file from the archive
   *
   * @see
   * org.murygin.archive.service.IArchiveService#getDocumentFile(java.lang.String)
   */
  @Override
  public byte[] getDocumentFile(String id) {
    Document document = getDocumentDao().load(id);
    if (document != null) {
      return document.getFileData();
    } else {
      return null;
    }
  }
  
  public Document getDocument(String id) {
    return getDocumentDao().load(id);
  }

  public IDocumentDao getDocumentDao() {
    return documentDao;
  }
}
