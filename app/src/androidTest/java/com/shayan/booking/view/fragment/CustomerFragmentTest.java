package com.shayan.booking.view.fragment;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.shayan.booking.R;
import com.shayan.booking.application.App;
import com.shayan.booking.dagger.DaggerServiceComponentMock;
import com.shayan.booking.dagger.ServiceComponentMock;
import com.shayan.booking.dagger.ServiceModuleMock;
import com.shayan.booking.view.activity.MainActivity;

/**
 * Created by Shayan on 7/27/2016.
 */
public class CustomerFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private CustomerFragment customerFragment;

    public CustomerFragmentTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //mocking injections
        App app = App.get(getInstrumentation().getTargetContext().getApplicationContext());
        app.setServiceComponent(createTestServiceComponent(app));

        customerFragment = getActivity().getCustomerFragment();
        getInstrumentation().waitForIdleSync();
    }

    public void testCancelButtonShouldBeHidden() {
        TextView cancelBtn = (TextView) customerFragment.getView().findViewById(R.id.btn_cancel_search);
        assertEquals("Cancel button is not hidden", false, cancelBtn.isShown());
    }

    public void testCustomerListShouldHasItems() {
        int itemCount = customerFragment.getCustomerAdapter().getItemCount();
        assertTrue("RecyclerView has no item", itemCount > 0);
    }

    public ServiceComponentMock createTestServiceComponent(Context context) {
        return DaggerServiceComponentMock.builder()
                .serviceModuleMock(new ServiceModuleMock(context))
                .build();
    }
}
