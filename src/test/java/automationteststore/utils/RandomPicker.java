package automationteststore.utils;

import org.openqa.selenium.WebElement;

import java.util.*;

public class RandomPicker {
    public static List<WebElement> getRandomElements(List<WebElement> list, int count) {


        if (list == null || list.isEmpty() || count <= 0) {
            return Collections.emptyList();
        }

        // Убираем дубликаты по data-id
        Map<String, WebElement> uniqueMap = new LinkedHashMap<>();
        for (WebElement element : list) {
            String id = element.getAttribute("data-id");
            if (id != null && !uniqueMap.containsKey(id)) {
                uniqueMap.put(id, element);
            }
        }

        List<WebElement> uniqueList = new ArrayList<>(uniqueMap.values());
        Collections.shuffle(uniqueList, new Random());

        List<WebElement> result = new ArrayList<>();
        for (int i = 0; i < Math.min(count, uniqueList.size()); i++) {
            result.add(uniqueList.get(i));
        }
        return result;
    }

}
