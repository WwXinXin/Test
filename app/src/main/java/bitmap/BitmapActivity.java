package bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.efamily.testapp.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BitmapActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnQualitCompress;
    private File mImageFile;
    private File mSdFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        mSdFile = Environment.getExternalStorageDirectory();
        mImageFile = new File(mSdFile, "");

        initView();
    }

    private void initView() {
        btnQualitCompress = (Button) findViewById(R.id.btn_aualit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_aualit:
                //加载文件
                //BitmapFactory.decodeFile(pathName);

                //加载资源文件
                //（1、设置图片分辨率密度）
                // (2、设置图片最终密度）
                // 对bitmap根据当前设备的屏幕像素密度来进行了缩放适配的操作，达到最佳显示效果
                //BitmapFactory.decodeResource(res);

                //加载流
                //BitmapFactory.decodeStream(in);

                //加载图片最终都是走向decodeStream方法
                //android源码中bitmap.cpp 底层的C源码

                qualitComPressToBitmap();

                break;
        }
    }

    /**
     * 质量压缩
     * 原理：通过算法同化（抠掉）了图片中的一些某个点附近相近的像素
     * 达到降低质量文件大小的目的
     * 减小了图片质量，像素不减少
     * 注意：它其实只能实现file的影响，对加载这个雨棚出来的bitmap内存是无法节省的，还是那么大
     * 因为bitmap在内存中的大小是按照像素计算的，也就是宽*高
     * 对于质量压缩，并不会改变图片真实的像素（像素大小不会变）
     * 质量压缩只是抠掉了相近的像素，但其整体像素并没有改变的
     * <p>
     * 使用场景：
     * 将图片压缩后保存到本地，或者将图片上传到服务器
     */
    private void qualitComPressToBitmap() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(mImageFile.getAbsolutePath(), options);
        //进行压缩
        compressImageToFile(bitmap, new File(mSdFile, "quality.jpeg"));
    }

    //降低图片质量
    public static void compressImageToFile(Bitmap bmp, File file) {
        int quality = 50;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //加读写权限
    }

    /**
     * 尺寸压缩
     * 通过减少单位尺寸的像素值，真正意义上的降低像素
     * 使用场景：缓存缩略图（头像处理）
     */
    public static void compressImageToFileBySize(Bitmap bmp, File file) {
        //压缩尺寸倍数，值越大，图片的尺寸越小
        int ratio = 4;
        Bitmap result = Bitmap.createBitmap(bmp
                .getWidth() / ratio, bmp.getHeight() / ratio, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(result);
        RectF rect = new RectF(0, 0, bmp.getWidth() / ratio, bmp.getHeight() / ratio);
        canvas.drawBitmap(bmp, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        result.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 采样率压缩
     * inJustDecodeBounds = true 不会真正加载图片，而是得到图片的宽高信息
     * inSampleSize 缩放采样率的比例 必须是2的倍数
     *
     *
     */
}
