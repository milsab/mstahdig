package edu.iit.sat.itmd4515.msabouri.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author Milad
 */
@Named
@ManagedBean
@ViewScoped
public class FileUploadController extends AbstractController implements Serializable {

    private static final Logger LOG = Logger.getLogger(OfferController.class.getName());

    private Part uploadedFile;
    FacesContext context = FacesContext.getCurrentInstance();
    ServletContext servletContext = (ServletContext) context
            .getExternalContext().getContext();
    String path = servletContext.getRealPath("");

    public Part getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(Part uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void saveFile(String fileName) {

        try (InputStream input = uploadedFile.getInputStream()) {
            String initName = uploadedFile.getSubmittedFileName();
            String extension = "." + initName.substring(initName.lastIndexOf(".") + 1);
            fileName = "i" + fileName; //uploadedFile.getSubmittedFileName();
            Files.copy(input, 
                    new File(path + File.separator + "resources"
                    + File.separator + "images" 
                    + File.separator, initName).toPath());
//                    + extension).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
