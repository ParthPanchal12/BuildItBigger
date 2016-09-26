package nanodegree.example.com.builditbigger;

import android.test.AndroidTestCase;
import android.text.TextUtils;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by parth panchal on 24-09-2016.
 */
public class AsyncJokeTest extends AndroidTestCase {
    private final CountDownLatch signal = new CountDownLatch(1);

    private class TestJokeAsyncTask extends AsyncTaskJoke {
        public TestJokeAsyncTask(iAsynTaskJokeCallback delegate) {
            super(delegate);
        }

        @Override
        protected void onPostExecute(String result) {
            assertTrue("Result should not be empty", !TextUtils.isEmpty(result));
            //assertFalse(true);
            signal.countDown();

        }
    }

    public void testJokeAsyncTaskResponse() throws Throwable {

        final TestJokeAsyncTask testJokeAsyncTask = new TestJokeAsyncTask(null);
        testJokeAsyncTask.execute();
        signal.await(); //Wait till AsyncTask finishes
    }

}
