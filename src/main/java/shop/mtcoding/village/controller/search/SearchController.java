package shop.mtcoding.village.controller.search;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.village.core.exception.Exception400;
import shop.mtcoding.village.core.exception.Exception401;
import shop.mtcoding.village.core.exception.MyConstException;
import shop.mtcoding.village.dto.ResponseDTO;
import shop.mtcoding.village.dto.search.SearchList;
import shop.mtcoding.village.dto.search.SearchOrderby;
import shop.mtcoding.village.dto.search.SearchRequest;
import shop.mtcoding.village.notFoundConst.PlaceConst;
import shop.mtcoding.village.notFoundConst.SearchConst;
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
        if (searchLists.isEmpty()) {
            throw new MyConstException(SearchConst.notfound);
        }


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
        List<SearchOrderby> SearchOrderbyPriceAsc = searchService.낮은가격순정렬();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(SearchOrderbyPriceAsc);
        return ResponseEntity.ok(responseDTO);
    }

    //
    @GetMapping("/star/high")
    public ResponseEntity<ResponseDTO<?>> searchPlaceByStarRatingDescending() {
        List<SearchOrderby> SearchOrderbyStarRatingDesc = searchService.별점높은순정렬();

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(SearchOrderbyStarRatingDesc);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/price")
    public ResponseEntity<ResponseDTO<?>> searchPlacesByKeywordAndPriceOrdering(@RequestParam String keyword, @RequestParam String ordering) {
        List<SearchList> searchLists = searchService.검색(keyword);
        if (searchLists.isEmpty()) {
            throw new MyConstException(SearchConst.notfound);
        }

        List<SearchOrderby> orderedSearchLists;
        switch (ordering) {
            case "high":
                orderedSearchLists = searchService.높은가격순정렬();
                break;
            case "low":
                orderedSearchLists = searchService.낮은가격순정렬();
                break;
            default:
                throw new MyConstException(SearchConst.notfound);
        }

        SearchRequest.SaveSearch saveSearch = new SearchRequest.SaveSearch();
        saveSearch.setKeyword(keyword);
        searchService.키워드저장(saveSearch);

        ResponseDTO<?> responseDTO = new ResponseDTO<>().data(orderedSearchLists);

        return ResponseEntity.ok().body(responseDTO);
    }


}
