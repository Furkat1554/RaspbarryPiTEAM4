package secured

import grails.validation.Validateable
import org.springframework.web.multipart.MultipartFile

class MediaFileCommand implements Validateable {

    MultipartFile mediaFile
    Long id
    static constraints = {
        id nullable: false
        mediaFile  validator: { val, obj ->
            if ( val == null ) {
                return false
            }
            if ( val.empty ) {
                return false
            }

            ['jpeg', 'jpg', 'png','mp3'].any { extension -> // <1>
                 val.originalFilename?.toLowerCase()?.endsWith(extension)
            }
        }
    }
}
