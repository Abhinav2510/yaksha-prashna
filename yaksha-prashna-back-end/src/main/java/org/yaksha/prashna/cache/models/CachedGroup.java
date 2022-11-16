package org.yaksha.prashna.cache.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CachedGroup {
    String groupId;
    private List<String> users;
}
