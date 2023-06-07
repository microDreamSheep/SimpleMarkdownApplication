package live.midreamsheep.simplemarkdown.editor.span.standard.quote;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.QuoteSpan;
import androidx.annotation.NonNull;

public class Quote  extends QuoteSpan {
    private int mLineColor;
    private int mLineWidth;

    public Quote(int color, int width) {
        super(color);
        mLineColor = color;
        mLineWidth = width;
    }

    @Override
    public void drawLeadingMargin(Canvas canvas, Paint paint, int left, int right, int top, int baseline, int bottom, CharSequence text, int start, int end, boolean isFirst, Layout layout) {
        // 绘制引用线
        paint.setColor(mLineColor);
        paint.setStrokeWidth(mLineWidth);
        canvas.drawLine(left + mLineWidth + 20, top + mLineWidth / 2, left + mLineWidth + 20, bottom - mLineWidth / 2, paint);

        // 绘制文本
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, start, end, left + mLineWidth + 20, baseline, paint);
    }

}
