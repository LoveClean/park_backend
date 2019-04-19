package com.springboot.framework.controller;

import com.springboot.framework.annotation.ACS;
import com.springboot.framework.config.ImageConfig;
import com.springboot.framework.contants.Errors;
import com.springboot.framework.controller.response.ImgUploadResponseBean;
import com.springboot.framework.service.OBSService;
import com.springboot.framework.util.ExceptionUtil;
import com.springboot.framework.util.ResponseEntity;
import com.springboot.framework.util.ResponseEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@SuppressWarnings("restriction")
@Api(description = "文件上传", produces = "application/json")
@RestController
@RequestMapping("/file")
public class FileUploadController {
    @Resource
    private OBSService obsService;
    @Resource
    private ImageConfig imageConfig;


    /**
     * 上传图片
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "上传图片", notes = "上传图片<br/>http://aligreen.alibaba.com/porn.html,在此检测rate超过80的为涉黄图片，会上传失败")
    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        // 尺寸验证
        String filePath = obsService.upload(file);
        return ResponseEntityUtil.success(filePath);
    }

    /**
     * 富文本内插入图片
     */
    @ACS(allowAnonymous = true)
    @ApiOperation(value = "富文本内插入图片", notes = "上传图片<br/>http://aligreen.alibaba.com/porn.html,在此检测rate超过80的为涉黄图片，会上传失败")
    @RequestMapping(value = "/uploadImageEdit", method = RequestMethod.POST)
    public ResponseEntity<ImgUploadResponseBean> uploadImageEdit(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String filePath = obsService.upload(file);
        return ResponseEntityUtil.success(new ImgUploadResponseBean(filePath));
    }

//  /**
//   * 上传图片
//   *
//   * @param file
//   * @param request
//   * @return
//   * @throws IOException
//   */
//  @ACS(allowAnonymous = true)
//  @ApiOperation(value = "上传图片ie浏览器", notes = "上传图片ie浏览器", consumes = "application/json")
//  @RequestMapping(value = "/uploadImageIe", method = RequestMethod.POST)
//  public void uploadImageIe(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response)
//      throws IOException {
//    // 尺寸验证
//    measurementValidation(file.getInputStream());
//    String filePath = obsService.upload(file);
//    response.setContentType("text/html");
//    PrintWriter out = response.getWriter();
//    out.write(filePath);
//    out.flush();
//    out.close();
//  }

//  /**
//   * 直接上传图片(base64Code方式)
//   *
//   * @param request
//   * @return
//   * @throws IOException
//   */
//  @ACS(allowAnonymous = true)
//  @ApiOperation(value = "上传图片base64Code方式", notes = "上传图片base64Code方式压缩", consumes = "application/json")
//  @RequestMapping(value = "/uploadImageBase64", method = RequestMethod.POST)
//  public ResponseEntity<String> uploadImageBase64(@RequestBody UploadImageBase64RequestBean bean, HttpServletRequest request)
//      throws IOException {
//    byte[] imgByte =
//        new BASE64Decoder().decodeBuffer(bean.getImage().substring(bean.getImage().indexOf(",") + 1, bean.getImage().length()));
//    // 尺寸验证
//    String filePath = obsService.uploadImageBase64(imgByte);
//    return ResponseEntityUtil.success(filePath);
//  }

//  /**
//   * 上传音频
//   * w
//   * @param file
//   * @param request
//   * @return
//   */
//  @ACS(allowAnonymous = true)
//  @ApiOperation(value = "上传音频", notes = "上传音频", consumes = "application/json")
//  @RequestMapping(value = "/uploadAudio", method = RequestMethod.POST)
//  public ResponseEntity<String> uploadAudio(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
//    String filePath = obsService.upload(file);
//    return ResponseEntityUtil.success(filePath);
//  }

    /**
     * 图片尺寸校验
     */
    private void measurementValidation(InputStream is) {
        BufferedImage source = null;
        try {
            source = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int owidth = source.getWidth();
        int oheight = source.getHeight();
        if (owidth > imageConfig.getWidth() || oheight > imageConfig.getHeight()) {
            ExceptionUtil.throwException(Errors.SYSTEM_CUSTOM_ERROR.code, "图片尺寸过大");
        }
    }
}
