package sample.boot.web.controller.image;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test/image")
public class ImageController {

    @ResponseBody
    @RequestMapping(value = "test.jpg", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public Resource jpg() {
//        Resource resource = new FileSystemResource("/Users/YamashiroRyota/workspace/tmp/img/seesar.jpg");
        final Resource resource = null;
        return resource;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "image/index";
    }

//    public Resource download() {
//        return new FileSystemResource("/Users/YamashiroRyota/workspace/tmp/img/seesar.jpg");
//    }


}
