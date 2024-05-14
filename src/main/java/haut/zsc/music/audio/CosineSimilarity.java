package haut.zsc.music.audio;

import java.util.Map;
import java.util.HashMap;

public class CosineSimilarity {  
  
    // 计算两个向量的点积  
    private static double dotProduct(Map<String, Double> vec1, Map<String, Double> vec2) {  
        double dotProduct = 0.0;  
        for (String key : vec1.keySet()) {  
            if (vec2.containsKey(key)) {  
                dotProduct += vec1.get(key) * vec2.get(key);  
            }  
        }  
        return dotProduct;  
    }
    private static double dotProduct2(Map<Integer, Long> vec1, Map<Integer, Long> vec2) {
        double dotProduct = 0.0;
        for (Integer key : vec1.keySet()) {
            if (vec2.containsKey(key)) {
                dotProduct += vec1.get(key) * vec2.get(key);
            }
        }
        return dotProduct;
    }
    // 计算向量的欧几里得长度（L2范数）  
    private static double magnitude(Map<String, Double> vector) {  
        double sum = 0.0;  
        for (Double value : vector.values()) {  
            sum += Math.pow(value, 2);  
        }  
        return Math.sqrt(sum);  
    }
    public static double cosineSimilarity2(Map<Integer, Long> vec1, Map<Integer, Long> vec2) {
        double sum1 = 0.0;
        double sum2 = 0.0;
        for (Integer integer : vec1.keySet()) {
            sum1 += Math.pow(vec1.get(integer), 2);
            if(vec2.containsKey(integer)){
                sum2 += Math.pow(vec2.get(integer), 2);
            }
        }
        double dotProduct = dotProduct2(vec1, vec2);
        double magnitude1 = Math.sqrt(sum1);
        double magnitude2 = Math.sqrt(sum2);
        return dotProduct / (magnitude1 * magnitude2);
    }
    // 计算两个向量的余弦相似度  
    public static double cosineSimilarity(Map<String, Double> vec1, Map<String, Double> vec2) {  
        double dotProduct = dotProduct(vec1, vec2);  
        double magnitude1 = magnitude(vec1);  
        double magnitude2 = magnitude(vec2);  
  
        if (magnitude1 == 0 || magnitude2 == 0) {  
            return 0; // 如果任一向量为零向量，则相似度为0  
        }
        return dotProduct / (magnitude1 * magnitude2);  
    }  
  
    public static void main(String[] args) {  
        // 示例向量  
        Map<String, Double> vec1 = new HashMap<>();  
        vec1.put("A", 1.10);
        vec1.put("B", 2.10);
        vec1.put("C", 3.10);
        vec1.put("D", 6.10);
  
        Map<String, Double> vec2 = new HashMap<>();  
        vec2.put("A", 1.10);
        vec2.put("B", 2.10);
        vec2.put("C", 3.10);
        vec2.put("D", 6.0);
        // 计算余弦相似度  
        double similarity = cosineSimilarity(vec1, vec2);  
        System.out.println("Cosine Similarity: " + similarity);



        // 示例向量
        Map<Integer, Long> vec3 = new HashMap<>();
        vec3.put(1, 2l);
        vec3.put(6, 1l);

        Map<Integer, Long> vec4 = new HashMap<>();
        vec4.put(1, 3l);
        vec4.put(0, 0l);


        // 计算余弦相似度
        double similarity2 = cosineSimilarity2(vec3, vec4);
        System.out.println("Cosine Similarity2: " + similarity2);
    }  
}