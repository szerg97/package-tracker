package com.szalai.packagetracker.service;

import com.szalai.packagetracker.controller.response.relationship.ForwardResponse;
import com.szalai.packagetracker.controller.response.relationship.DistributionResponse;
import com.szalai.packagetracker.controller.response.relationship.ArrivalResponse;
import com.szalai.packagetracker.controller.response.relationship.PostResponse;
import com.szalai.packagetracker.model.node.Customer;
import com.szalai.packagetracker.model.relationship.Forward;
import com.szalai.packagetracker.model.relationship.Distribution;
import com.szalai.packagetracker.model.relationship.Arrival;
import com.szalai.packagetracker.model.relationship.Post;
import org.springframework.stereotype.Service;

@Service
public class RelationshipMapper {

    public PostResponse postToResponse(Post post){
        return new PostResponse(
                post.getId(),
                post.getStatus(),
                post.getDateTime(),
                post.getDistributionPoint().getId()
        );
    }

    public DistributionResponse distributionToResponse(Distribution post){
        return new DistributionResponse(
                post.getId(),
                post.getStatus(),
                post.getDateTime(),
                post.getWarehouse().getId()
        );
    }

    public ForwardResponse forwardToResponse(Forward forward){
        if (forward == null){
            return null;
        }
        return new ForwardResponse(
                forward.getId(),
                forward.getStatus(),
                forward.getDateTime(),
                forward.getWarehouse().getId()
        );
    }

    public ArrivalResponse arrivalToResponse(Arrival arrival) {
        return new ArrivalResponse(
                arrival.getId(),
                arrival.getStatus(),
                arrival.getDateTime(),
                arrival.getWarehouse().getId()
        );
    }
}
