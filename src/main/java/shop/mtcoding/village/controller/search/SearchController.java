package shop.mtcoding.village.controller.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;
import shop.mtcoding.village.dto.search.SearchRequest;
import shop.mtcoding.village.service.SearchService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final SearchService searchService;



    @GetMapping
    public ResponseEntity<ResponseDTO<?>> searchPlacesByKeyword(@RequestParam String keyword) {
        List<SearchList> searchLists = searchService.검색(keyword);


            SearchRequest.SaveSearch saveSearch = new SearchRequest.SaveSearch();
            saveSearch.setKeyword(keyword);
            searchService.키워드저장(saveSearch);

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(searchLists);


        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/price/high")
    public ResponseEntity<ResponseDTO<?>> searchPlacesByPriceDescending() {
        List<SearchOrderby> SearchOrderbyPriceDesc = searchService.높은가격순정렬();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(SearchOrderbyPriceDesc);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/price/low")
    public ResponseEntity<ResponseDTO<?>> searchPlacesByPriceAscending() {
        List<SearchOrderby> SearchOrderbPriceAsc = searchService.낮은가격순정렬();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(SearchOrderbPriceAsc);
        return ResponseEntity.ok(responseDTO);
    }
//
    @GetMapping("/star/high")
    public ResponseEntity<ResponseDTO<?>> searchPlaceByStarRaingDescending() {
        List<SearchOrderby> SearchOrderbyStarRatingDesc = searchService.별점높은순정렬();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(SearchOrderbyStarRatingDesc);
        return ResponseEntity.ok(responseDTO);
    }

}
