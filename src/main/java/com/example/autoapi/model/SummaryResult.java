package com.example.autoapi.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SummaryResult {
    private long totalCount;
    private long successCount;
    private long failureCount;
    private long startTime;
    private long endTime;
    private List<FailureResult> failureResults;
}
