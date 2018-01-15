package com.example.sampleswagger.rest.api;

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
    @ApiModelProperty("ページ")
    @Min(1)
    @Max(100)
    private Integer page;

    @ApiModelProperty("サイズ")
    @Min(1)
    @Max(100)
    private Integer size;

    @ApiModelProperty(value = "キーワード", name = "key_word")
    @Size(min = 1, max = 100)
    private String keyWord;
}
