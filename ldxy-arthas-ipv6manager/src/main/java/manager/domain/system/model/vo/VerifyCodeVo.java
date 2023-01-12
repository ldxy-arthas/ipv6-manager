package manager.domain.system.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;

/**
 * @Author: yuluo
 * @CreateTime: 2023-01-12  11:37
 * @Description: TODO
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerifyCodeVo {

    private String base64ImgEncoding;

    private String codeKey;

}
