package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Clothes;
import com.example.demo.entity.Weather;
import com.example.demo.entity.enums.CategoryType;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RecommendService {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ClothesService clothesService;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<List<Clothes>> recommendBaseClothes(String city) throws IOException {

        Weather weather = weatherService.getWeather(city);
//        1111111111 - id админа
        List<Clothes> clothes = clothesService.findByAddedBy(1111111111);
        List<Clothes> clothesSuitable = new ArrayList<>();
        List<List<Clothes>> clothesAlmostRecommend = new ArrayList<>();

//получаем всю одежду, подходящую по погоде
        for (Clothes clothes_item : clothes) {
            Category category = categoryRepository.findById(clothes_item.getCategoryId().getCategoryId());

            assert category.getTempMin() > weather.getTemp() &&
                    category.getTempMax() < weather.getTemp();

            assert category.getWindMin() > weather.getWind() &&
                    category.getWindMax() < weather.getWind();

            assert category.getHumidityMin() > weather.getHumidity() &&
                    category.getHumidityMax() < weather.getHumidity();

            assert category.getClouds_min() > weather.getClouds() &&
                    category.getClouds_max() < weather.getClouds();

            assert category.getVisibilityMin() > weather.getVisibility() &&
                    category.getVisibilityMax() < weather.getVisibility();

            clothesSuitable.add(clothes_item);
        }

//        List<Clothes> clothes_set = new ArrayList<>();
// составлеям комлект низ+верх
        for (Clothes clothes_top : clothesSuitable) {
            CategoryType type_top = clothes_top.getCategoryId().getCategoryType();
            if (type_top == CategoryType.TOP) {
                for (Clothes clothes_bottom : clothesSuitable) {
                    CategoryType type_bottom = clothes_bottom.getCategoryId().getCategoryType();

                    if (type_bottom == CategoryType.BOTTOM) {
                        List<Clothes> clothes_set = new ArrayList<>();
                        clothes_set.add(clothes_top);
                        clothes_set.add(clothes_bottom);
                        clothesAlmostRecommend.add(clothes_set);
                    }
                }
            }
        }

//        добавляем full-одежду
        for (Clothes clothes_full : clothesSuitable) {
            CategoryType type_top = clothes_full.getCategoryId().getCategoryType();
            if (type_top == CategoryType.FULL) {
                List<Clothes> clothes_set = new ArrayList<>();
                clothes_set.add(clothes_full);
                clothesAlmostRecommend.add(clothes_set);
            }
        }

//добавляем обувь
        List<List<Clothes>> clothesAlmostRecommend_2 = new ArrayList<>();
        for (Clothes shoes : clothesSuitable) {
            CategoryType type_shoes = shoes.getCategoryId().getCategoryType();
            if (type_shoes == CategoryType.SHOES) {
                for (List<Clothes> clothes_recommend : clothesAlmostRecommend) {
                    List<Clothes> clothesWithShoes = new ArrayList<>(clothes_recommend);
                    clothesWithShoes.add(shoes);
                    clothesAlmostRecommend_2.add(clothesWithShoes);
                }
            }
        }

        //добавляем верхнюю одежду
        List<List<Clothes>> clothesAlmostRecommend_3 = new ArrayList<>();
        for (Clothes outerwear : clothesSuitable) {
            CategoryType type_outerwear = outerwear.getCategoryId().getCategoryType();
            if (type_outerwear == CategoryType.OUTERWEAR) {
                for (List<Clothes> clothes_recommend : clothesAlmostRecommend_2) {
                    List<Clothes> clothesWithOuterwear = new ArrayList<>(clothes_recommend);
                    clothesWithOuterwear.add(outerwear);
                    clothesAlmostRecommend_3.add(clothesWithOuterwear);
                }
            }
        }

        //добавляем головно  убор
        List<List<Clothes>> clothesAlmostRecommend_4 = new ArrayList<>();
        for (Clothes headdress : clothesSuitable) {
            CategoryType type_headdress = headdress.getCategoryId().getCategoryType();
            if (type_headdress == CategoryType.HEADDRESS) {
                for (List<Clothes> clothes_recommend : clothesAlmostRecommend_3) {
                    List<Clothes> clothesWithHeaddress = new ArrayList<>(clothes_recommend);
                    clothesWithHeaddress.add(headdress);
                    clothesAlmostRecommend_4.add(clothesWithHeaddress);
                }
            }
        }


        return clothesAlmostRecommend_4;
    }

    public List<List<Clothes>> recommendOptionalClothes(String city, int user_id) throws IOException {

        Weather weather = weatherService.getWeather(city);
//        1111111111 - id админа
        List<Clothes> clothes = clothesService.findByAddedBy(user_id);
        List<Clothes> clothesSuitable = new ArrayList<>();
        List<List<Clothes>> clothesAlmostRecommend = new ArrayList<>();

//получаем всю одежду, подходящую по погоде
        for (Clothes clothes_item : clothes) {
            Category category = categoryRepository.findById(clothes_item.getCategoryId().getCategoryId());

            if (category.getTempMin() > weather.getTemp() &&
                    category.getTempMax() < weather.getTemp() &&
                    category.getWindMin() > weather.getWind() &&
                    category.getWindMax() < weather.getWind() &&
                    category.getHumidityMin() > weather.getHumidity() &&
                    category.getHumidityMax() < weather.getHumidity() &&
                    category.getClouds_min() > weather.getClouds() &&
                    category.getClouds_max() < weather.getClouds() &&
                    category.getVisibilityMin() > weather.getVisibility() &&
                    category.getVisibilityMax() < weather.getVisibility()) ;

            clothesSuitable.add(clothes_item);
        }

// составлеям комлект низ+верх
        for (Clothes clothes_top : clothesSuitable) {
            CategoryType type_top = clothes_top.getCategoryId().getCategoryType();
            if (type_top == CategoryType.TOP) {
                for (Clothes clothes_bottom : clothesSuitable) {
                    CategoryType type_bottom = clothes_bottom.getCategoryId().getCategoryType();

                    if (type_bottom == CategoryType.BOTTOM) {
                        List<Clothes> clothes_set = new ArrayList<>();
                        clothes_set.add(clothes_top);
                        clothes_set.add(clothes_bottom);
                        clothesAlmostRecommend.add(clothes_set);
                    }
                }
            }
        }

//        добавляем full-одежду
        for (Clothes clothes_full : clothesSuitable) {
            CategoryType type_top = clothes_full.getCategoryId().getCategoryType();
            if (type_top == CategoryType.FULL) {
                List<Clothes> clothes_set = new ArrayList<>();
                clothes_set.add(clothes_full);
                clothesAlmostRecommend.add(clothes_set);
            }
        }

//добавляем обувь
        List<List<Clothes>> clothesAlmostRecommend_2 = new ArrayList<>();
        for (Clothes shoes : clothesSuitable) {
            CategoryType type_shoes = shoes.getCategoryId().getCategoryType();
            if (type_shoes == CategoryType.SHOES) {
                for (List<Clothes> clothes_recommend : clothesAlmostRecommend) {
                    List<Clothes> clothesWithShoes = new ArrayList<>(clothes_recommend);
                    clothesWithShoes.add(shoes);
                    clothesAlmostRecommend_2.add(clothesWithShoes);
                }
            }
        }

        //добавляем верхнюю одежду
        List<List<Clothes>> clothesAlmostRecommend_3 = new ArrayList<>();
        for (Clothes outerwear : clothesSuitable) {
            CategoryType type_outerwear = outerwear.getCategoryId().getCategoryType();
            if (type_outerwear == CategoryType.OUTERWEAR) {
                for (List<Clothes> clothes_recommend : clothesAlmostRecommend_2) {
                    List<Clothes> clothesWithOuterwear = new ArrayList<>(clothes_recommend);
                    clothesWithOuterwear.add(outerwear);
                    clothesAlmostRecommend_3.add(clothesWithOuterwear);
                }
            }
        }

        //добавляем головно  убор
        List<List<Clothes>> clothesAlmostRecommend_4 = new ArrayList<>();
        for (Clothes headdress : clothesSuitable) {
            CategoryType type_headdress = headdress.getCategoryId().getCategoryType();
            if (type_headdress == CategoryType.HEADDRESS) {
                for (List<Clothes> clothes_recommend : clothesAlmostRecommend_3) {
                    List<Clothes> clothesWithHeaddress = new ArrayList<>(clothes_recommend);
                    clothesWithHeaddress.add(headdress);
                    clothesAlmostRecommend_4.add(clothesWithHeaddress);
                }
            }
        }

        return clothesAlmostRecommend_4;
    }

}
