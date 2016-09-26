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

//    public void testJokeAsyncTaskResponse() throws Throwable {
//
//        final TestJokeAsyncTask testJokeAsyncTask = new TestJokeAsyncTask(null);
//        testJokeAsyncTask.execute();
//        signal.await(); //Wait till AsyncTask finishes
//    }
//
//    public void testResponse(){
//        final CountDownLatch cd=new CountDownLatch(1);
//        Long start=new Date().getTime();
//
//        new AsyncTaskJoke().execute(new iAsynTaskJokeCallback() {
//            @Override
//            public void callbackAsync(String joke) {
//                assertNotNull(joke);
//                cd.countDown();
//            }
//        });
//
//        try{
//            if(!cd.await(30, TimeUnit.SECONDS))
//            {
//                Long end = (new Date().getTime() - start) / 1000;
//                throw new AssertionError("Service not responding after " + end + " seconds");
//            }
//
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//    }

}
