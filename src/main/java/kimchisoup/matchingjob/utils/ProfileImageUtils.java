package kimchisoup.matchingjob.utils;

import kimchisoup.matchingjob.error.ProfileImageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Component
public class ProfileImageUtils {
    @Value("${profile_image_path}")
    private String profileImagePath;

    public URL saveImageAndReturnUrl(String email, MultipartFile profileImage) {
        if (profileImage == null) {
            return null;
        }
        File dir = new File(profileImagePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File profileImageFile = new File(profileImagePath + profileImage.getOriginalFilename());
        try {
            profileImage.transferTo(profileImageFile);
            return profileImageFile.toURI().toURL();
        } catch (IOException e) {
            throw new ProfileImageException(e.getMessage());
        }
    }

}
