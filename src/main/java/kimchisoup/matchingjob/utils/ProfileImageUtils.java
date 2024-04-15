package kimchisoup.matchingjob.utils;

import kimchisoup.matchingjob.error.ProfileImageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

@Component
public class ProfileImageUtils {
    @Value("${profile_image_directory}")
    private String profileImageDirectory;
    @Value("${profile_default_image_path}")
    private String profileDefaultImagePath;

    public URL saveImageAndReturnUrl(MultipartFile profileImage) {
        if (profileImage == null) {
            try {
                return new URL(profileDefaultImagePath);
            } catch (MalformedURLException e) {
                throw new ProfileImageException(e.getMessage());
            }
        }
        File dir = new File(profileImageDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File profileImageFile = new File(String.format("%s\\%s.%s", profileImageDirectory, UUID.randomUUID(), StringUtils.getFilenameExtension(profileImage.getOriginalFilename())));
        try {
            profileImage.transferTo(profileImageFile);
            return profileImageFile.toURI().toURL();
        } catch (IOException e) {
            throw new ProfileImageException(e.getMessage());
        }
    }

}
