package cn.violin.onenote.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Column;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {

    @JsonProperty("item_id")
    private long itemId;

    @JsonProperty("item_name")
    private String itemName;

    @JsonProperty("item_value")
    private String itemValue;

    @JsonProperty("category_id")
    private long categoryId;

}
