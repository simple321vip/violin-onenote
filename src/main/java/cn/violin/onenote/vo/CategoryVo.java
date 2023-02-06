package cn.violin.onenote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryVo {

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("category_color")
    private String categoryColor;

    @JsonProperty("category_icon")
    private String categoryIcon;

    @JsonProperty("category_theme")
    private String categoryTheme;

}
