package com.szalai.packagetracker.controller.response;

import com.szalai.packagetracker.controller.response.relationship.DistributionResponse;
import com.szalai.packagetracker.controller.response.relationship.ArrivalResponse;
import com.szalai.packagetracker.controller.response.relationship.ForwardResponse;
import com.szalai.packagetracker.controller.response.relationship.PostResponse;

public record TrackResponse(PostResponse post, DistributionResponse distribution, ForwardResponse forward, ArrivalResponse arrival){}
