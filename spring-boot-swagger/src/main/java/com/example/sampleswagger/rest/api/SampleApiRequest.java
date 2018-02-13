package com.example.sampleswagger.rest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel("サンプルリクエスト")
public class SampleApiRequest {
    @ApiModelProperty(value = "ページ")
    @Min(1)
    @Max(100)
    @JsonProperty
    private Integer page = 1;

    @ApiModelProperty("サイズ")
    @Min(1)
    @Max(100)
    @JsonProperty
    private Integer size = 10;

    @ApiModelProperty(value = "キーワード", name = "key_word")
    @Size(max = 100)
    @JsonProperty
    private String keyWord;
}
