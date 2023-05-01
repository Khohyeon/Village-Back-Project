package shop.mtcoding.village.dto.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.mtcoding.village.dto.file.response.FileList;
import shop.mtcoding.village.dto.hashtag.response.HashtagList;
import shop.mtcoding.village.model.hashtag.Hashtag;
import shop.mtcoding.village.model.scrap.Scrap;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchList {
    private Long id;
    private String title;
    private Integer maxPeople;
    private Integer maxParking;
    private Integer pricePerHour;
    private String keyword;
    private String sigungu;
    private Integer starRating;
    private Long reviewCount;
    private List<HashtagList> hashtags = new ArrayList<>();
    private List<FileList> fileUrls = new ArrayList<>();
    private Scrap scrap = null;



}
