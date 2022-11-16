package org.yaksha.prashna.cache.models;

import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Data
@Builder
public class CachedUser {
    private String userName;
    private Map<Integer,Integer> quizMap;
}
