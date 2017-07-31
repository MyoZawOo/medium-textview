package io.github.angebagui.mediumtextview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.jsoup.nodes.Element;

import io.github.angebagui.mediumtextview.span.BlockQuoteSpan;
import io.github.angebagui.mediumtextview.util.Utils;

/**
 * Created by angebagui on 06/08/2016.
 */

public class BlockquoteView extends ElementView{

    public static final String TAG = BlockquoteView.class.getSimpleName();


    public BlockquoteView(Context context, Element element) {
        super(context, element);
    }

    public BlockquoteView(Context context, AttributeSet attrs, Element element) {
        super(context, attrs, element);
    }

    @Override
    public void render() {
        setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        setOrientation(VERTICAL);

        if (getElement() != null){
            if (getElement().children().size() > 0){
                Utils.appendView(this, getElement().children());
            }

            if (!getElement().text().isEmpty()){
                Log.d(TAG, "Text : "+getElement().text());
                setText(getElement().text());
            }
        }
    }


    public void setText(String text){
        Spannable textToSpan = new SpannableString(text);
        textToSpan.setSpan(new BlockQuoteSpan(getResources().getColor(android.R.color.black), 16, 16), 0, text.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT
        ));
        textView.setText(textToSpan);
        textView.setPadding(Utils.dpToPx(getContext(), 16), 0, Utils.dpToPx(getContext(), 16), 0);
        final Typeface myTypeface = Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/"+"freight_text_pro.ttf");
        textView.setTypeface(myTypeface, 0);
        textView.setTextSize(18f);
        textView.setTextColor(getResources().getColor(android.R.color.black));

        addView(textView);
    }
}
