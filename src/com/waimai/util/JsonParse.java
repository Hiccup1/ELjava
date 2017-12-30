package com.waimai.util;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.waimai.model.Dish;
import com.waimai.model.OrderItem;

import java.lang.reflect.Type;

public class JsonParse {
    public static List<OrderItem> getOrderItem(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<OrderItem>>(){}.getType();
        List<OrderItem> list = gson.fromJson(json, listType);
        return list;
    }



    public static JsonArray  getDishTOjson(List<Dish> list) {
        JsonArray  jsonArray = new Gson().toJsonTree(list, new TypeToken<List<Dish>>() {}.getType()).getAsJsonArray();
        return jsonArray;
    }

}
