package com.ieeecsvit.riviera17android;

/**
 * Created by Karishnu Poddar on 16/10/2016.
 */

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ieeecsvit.riviera17android.models.AcceptRequest;
import com.ieeecsvit.riviera17android.models.ChangeRequests;
import com.ieeecsvit.riviera17android.models.LoginResponse;
import com.ieeecsvit.riviera17android.rest.ApiClient;
import com.ieeecsvit.riviera17android.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVChangeResponseAdapter extends RecyclerView.Adapter<RVChangeResponseAdapter.ChangeResponseItemViewHolder> {

    private List<ChangeRequests> changeList;
    private Activity context;
    ViewGroup parent;

    class ChangeResponseItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvEvent, tvStatus;
        RecyclerView recyclerViewChanges;
        Button acceptBt, declineBt;

        ChangeResponseItemViewHolder(View view) {
            super(view);

            tvEvent = (TextView) view.findViewById(R.id.tvEvent);
            tvStatus = (TextView) view.findViewById(R.id.tvStatus);

            recyclerViewChanges = (RecyclerView) view.findViewById(R.id.rvChanges);
            recyclerViewChanges.setLayoutManager(new CustomLinearLayoutManager(context));

            acceptBt = (Button) view.findViewById(R.id.acceptButton);
            declineBt = (Button) view.findViewById(R.id.declineButton);
        }
    }

    public RVChangeResponseAdapter(List<ChangeRequests> changeList, Activity context) {
        this.changeList = changeList;
        this.context = context;
    }

    @Override
    public ChangeResponseItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_change_response, parent, false);

        return new ChangeResponseItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ChangeResponseItemViewHolder holder, final int position) {

        if(changeList.get(position).eventFor!=null) {
            holder.tvEvent.setText(changeList.get(position).eventFor.eventName);
        }

        if(changeList.get(position).approved.approvalStatus!=null){
            if(changeList.get(position).approved.approvalStatus) {
                holder.tvStatus.setText("APPROVED");
            }
            else{
                holder.tvStatus.setText("DECLINED");
            }
            holder.acceptBt.setVisibility(View.GONE);
            holder.declineBt.setVisibility(View.GONE);
        }
        else{
            holder.tvStatus.setText("NOT APPROVED");
            holder.acceptBt.setVisibility(View.VISIBLE);
            holder.declineBt.setVisibility(View.VISIBLE);
        }


        holder.recyclerViewChanges.setAdapter(new RVChangeAdapter(changeList.get(position).changes, context));

        final ApiInterface apiInterface = new ApiClient().getClient(context).create(ApiInterface.class);
        final AcceptRequest acceptRequest = new AcceptRequest();

        acceptRequest.requestId = changeList.get(position).id;

        holder.acceptBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptRequest.accept = true;
                Call<LoginResponse> loginRequestCall = apiInterface.acceptEvent(acceptRequest);
                loginRequestCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        context.finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });

        holder.declineBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                acceptRequest.accept = false;
                Call<LoginResponse> loginRequestCall = apiInterface.acceptEvent(acceptRequest);
                loginRequestCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        context.finish();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return changeList.size();
    }
}
