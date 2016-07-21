package example.expense.user.app.expense;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import example.expense.user.app.R;
import example.expense.user.app.common.ErrorUtils;

/**
 * Created by dilky on 2016-07-20.
 * 상세보기 화면
 */
public class ViewExpense extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {

            setContentView(R.layout.content_view_expense);

        } catch (Exception e) {
            ErrorUtils.AlertException(this, getString(R.string.error_msg_default_with_activity), e);
        }
    }
}
