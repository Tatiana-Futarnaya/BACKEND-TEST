package by.clevertec.model.memory;

import by.clevertec.model.beans.cards.Cards;


import java.util.HashMap;
import java.util.Map;

public class CardsMemory {
    private static Map<String, Cards> cardsMap = new HashMap<>();


    static {
        cardsMap.put("card-1234",new Cards("1234",2));
        cardsMap.put("card-1235",new Cards("1235",3));
        cardsMap.put("card-1236",new Cards("1236",5));
        cardsMap.put(null,new Cards(null,0));

    }

    public static Cards getById(String id)  {
        String card=id;
        for(Map.Entry<String, Cards> item : cardsMap.entrySet()){
            if(id!=item.getKey()){
                id=null;
            }
        }
        return cardsMap.get(card);
    }
}
