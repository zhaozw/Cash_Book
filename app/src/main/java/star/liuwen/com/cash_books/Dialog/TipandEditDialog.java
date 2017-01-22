package star.liuwen.com.cash_books.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import star.liuwen.com.cash_books.R;

/**
 * Created by liuwen on 2017/1/22.
 */
public class TipAndEditDialog extends Dialog implements View.OnClickListener {
    private TextView mTvLeft, mTvRight, mTvTitle, mTvMessage;
    private EditText mEdMessage;
    private ITipDialogListener mListener;
    private String mContent = "";

    public TipAndEditDialog(Context context, String content) {
        super(context, R.style.FullScreenDialog);
        this.mContent = content;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loginout);
        mTvLeft = (TextView) findViewById(R.id.dialog_left);
        mTvRight = (TextView) findViewById(R.id.dialog_right);
        mTvTitle = (TextView) findViewById(R.id.dialog_hint);
        mTvMessage = (TextView) findViewById(R.id.dialog_tv);
        mEdMessage = (EditText) findViewById(R.id.dialog_edit);

        mTvTitle.setText(mContent);

        mTvLeft.setOnClickListener(this);
        mTvRight.setOnClickListener(this);
    }


    public void setTvMessage(String text) {
        if (mTvMessage != null) {
            mTvMessage.setVisibility(View.VISIBLE);
            mTvMessage.setText(text);
        }
    }

    public void setHintMessage(String text) {
        if (mEdMessage != null) {
            mEdMessage.setVisibility(View.VISIBLE);
            mEdMessage.setHint(text);
        }
    }

    public void setInputMessage(String text) {
        if (mEdMessage != null) {
            mEdMessage.setVisibility(View.VISIBLE);
            mEdMessage.setText(text);
        }
    }


    public interface ITipDialogListener {
        void clickLeft();

        void clickRight();
    }


    public void setListener(ITipDialogListener listener) {
        mListener = listener;
    }


    @Override
    public void onClick(View v) {
        if (v == mTvLeft) {
            if (mListener != null) {
                mListener.clickLeft();
                dismiss();
            }
        } else if (v == mTvRight) {
            if (v == mTvRight) {
                mListener.clickRight();
                dismiss();
            }
        }

    }
}
