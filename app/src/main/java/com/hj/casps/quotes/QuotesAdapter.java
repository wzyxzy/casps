package com.hj.casps.quotes;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.hj.casps.R;
import com.hj.casps.adapter.WZYBaseAdapter;

import java.util.List;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by zy on 2017/4/20.
 */

public class QuotesAdapter extends WZYBaseAdapter<QuoteModel> {
    private Context context;

    public QuotesAdapter(List<QuoteModel> data, Context context, int layoutRes) {
        super(data, context, layoutRes);
        this.context = context;
    }

    @Override
    public void bindData(ViewHolder holder, final QuoteModel quoteModel) {
        TextView quotes_query_no = (TextView) holder.getView(R.id.quotes_query_no);
        quotes_query_no.setText(quoteModel.getSerial_no());
        TextView quotes_name = (TextView) holder.getView(R.id.quotes_name);
        quotes_name.setText(quoteModel.getName());
        TextView quotes_person = (TextView) holder.getView(R.id.quotes_person);
        quotes_person.setText(quoteModel.getPerson());
        TextView quotes_query_quantity = (TextView) holder.getView(R.id.quotes_query_quantity);
        quotes_query_quantity.setText(quoteModel.getNumber());
        TextView quotes_query_price = (TextView) holder.getView(R.id.quotes_query_price);
        quotes_query_price.setText(quoteModel.getPrice());
        TextView quotes_query_period = (TextView) holder.getView(R.id.quotes_query_period);
        quotes_query_period.setText(quoteModel.getPeriod());
        FancyButton quote_assign_Btn = (FancyButton) holder.getView(R.id.quote_assign_Btn);
        quote_assign_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        FancyButton quote_edit_Btn = (FancyButton) holder.getView(R.id.quote_edit_Btn);
        quote_edit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(context.getApplicationContext(), CreateQuotes.class);
                intent.putExtra("data",quoteModel);
//                intent.putExtra("name", quoteModel.getName());
//                intent.putExtra("number", quoteModel.getNumber());
//                intent.putExtra("price", quoteModel.getPrice());
//                intent.putExtra("period", quoteModel.getPeriod());
                context.getApplicationContext().startActivity(intent);

            }
        });
    }
}
