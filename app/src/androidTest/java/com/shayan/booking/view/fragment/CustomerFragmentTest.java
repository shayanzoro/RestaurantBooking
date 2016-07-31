package com.shayan.booking.view.fragment;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.shayan.booking.R;
import com.shayan.booking.application.App;
import com.shayan.booking.dagger.DaggerServiceComponentMock;
import com.shayan.booking.dagger.ServiceComponentMock;
import com.shayan.booking.dagger.ServiceModuleMock;
import com.shayan.booking.db.DataBaseManager;
import com.shayan.booking.util.GeneralUtils;
import com.shayan.booking.util.ShayanLogger;
import com.shayan.booking.view.activity.MainActivity;

import javax.inject.Inject;

/**
 * Created by Shayan on 7/27/2016.
 */
public class CustomerFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {

    @Inject
    DataBaseManager dataBaseManager;

    private CustomerFragment customerFragment;
    private Context context;

    public CustomerFragmentTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        //mocking injections
        context = getInstrumentation().getTargetContext().getApplicationContext();

        ServiceComponentMock serviceComponentMock = DaggerServiceComponentMock.builder()
                .serviceModuleMock(new ServiceModuleMock(context))
                .build();

        App.get(context).setServiceComponent(serviceComponentMock);
        serviceComponentMock.inject(this);

        customerFragment = getActivity().getCustomerFragment();

        getInstrumentation().waitForIdleSync();
    }

    public void testCancelButtonShouldBeHidden() {
        TextView cancelBtn = (TextView) customerFragment.getView().findViewById(R.id.btn_cancel_search);
        assertEquals("Cancel button is not hidden", false, cancelBtn.isShown());
    }

    public void testCustomerListShouldHasItems() {
        int itemCount = customerFragment.getCustomerAdapter().getItemCount();
        ShayanLogger.d("testCustomerListShouldHasItems", "itemCount=" + itemCount);

        if (GeneralUtils.isOnline(context)) {
            //test when device is online
            assertTrue("CustomerList has no item in online mode", itemCount > 0);
        } else {
            //test when device is offline
            assertEquals("CustomerList item count is wrong in offline mode", dataBaseManager.getCustomersCount(), itemCount);
        }
    }

}
