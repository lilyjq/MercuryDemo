package com.mercury.demo.camera;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.mercury.demo.R;
import com.mercury.demo.util.ImageUtil;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import pub.devrel.easypermissions.EasyPermissions;

public class AvatarActivity extends AppCompatActivity implements View.OnClickListener, EasyPermissions.PermissionCallbacks {


    ImageView mAvatar;
    TextView mTakePhotoTv;
    TextView mOpenAblumTv;
    TextView mClearSpTv;
    private String dbName = "data";
    private String dbAvatarKey = "avatar";
    private int RC_OPENABLUM = 101;
    private int RC_OPENCAMRA = 102;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_avatar);
        mAvatar = findViewById(R.id.avatar);
        mTakePhotoTv = findViewById(R.id.take_photo);
        mOpenAblumTv = findViewById(R.id.open_ablum);
        mClearSpTv = findViewById(R.id.clear_sp);


        SharedPreferences sharedPreferences = getSharedPreferences(dbName, Context.MODE_PRIVATE);
        String path = sharedPreferences.getString(dbAvatarKey, "");
       if(getPath(path) != null){
           Glide.with(this).load(getPath(path)).apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.u).into(mAvatar);
       }else{
           Glide.with(this).load(path).apply(RequestOptions.circleCropTransform()).placeholder(R.drawable.u).into(mAvatar);
       }

        mOpenAblumTv.setOnClickListener(this);
        mTakePhotoTv.setOnClickListener(this);
        mClearSpTv.setOnClickListener(this);
    }


    private void savaPath(String path) {
        SharedPreferences sharedPreferences = getSharedPreferences(dbName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(dbAvatarKey, path);
        editor.apply();
    }

    private void clearSP(){
        SharedPreferences sharedPreferences = getSharedPreferences(dbName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(dbAvatarKey, "");
        editor.apply();
        Toast.makeText(this,"sp is cleaed ...",Toast.LENGTH_SHORT).show();
    }


    private Uri getPath(String path) {

        if(!TextUtils.isEmpty(path)){
            if(path.startsWith("content:")) {
                return getImageContentUri(path);
            }
        }
        return  null;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.take_photo:
                requestPermission();
                break;
            case R.id.open_ablum:
                openAblum();
                break;
            case R.id.clear_sp:
                clearSP();
                break;
            default:
                break;
        }
    }

    String[] perStr = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private void requestPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA) && EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            startCamera();
        } else {
            EasyPermissions.requestPermissions(this, "askquestion", 22, perStr);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        if (perms.)
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "we need permission to take photo and save photo!", Toast.LENGTH_SHORT).show();

    }

    Uri photoUri;
    File tempFile;

    private void startCamera() {
        String bitmapPath = Environment.getExternalStorageDirectory().getPath() + "/MercuryDemo/pic/" + Calendar.getInstance().getTime() + ".jpg";
//        Intent intent = new Intent("android.media.action.IMGAE_CAPTURE");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //当指定的aty组件没有找到是会抛出ActivityNotFoundException异常并直接崩溃，此时可以通过resolveActivity方式先判断
        if (intent.resolveActivity(getPackageManager()) != null) {
            photoUri = null;

            tempFile = createNewFile(bitmapPath, true);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                ContentValues contentValues = new ContentValues();
//                contentValues.put(MediaStore.Images.Media.DATA,bitmapPath);
//                photoUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,contentValues);
//                photoUri = getMediaUriFromPath(bitmapPath);
                photoUri = createQUri();


            } else {
                if (tempFile != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        photoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", tempFile);
                    } else {
                        photoUri = Uri.fromFile(tempFile);
                    }
                }
            }
            if (photoUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                startActivityForResult(intent, RC_OPENCAMRA);
            }

        }
    }


    public String getFilePath(Context context, final Uri uri) {
        if (null == uri) return null;

        final String scheme = uri.getScheme();
        String data = null;

        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    private Uri createQUri() {
        String status = Environment.getExternalStorageState();
        ContentValues contentValues = new ContentValues();
        //不能指定相对路径 Primary directory (invalid) not allowed for content://media/external/images/media; allowed directories are [DCIM, Pictures]
        //只能指定已经存在的路径
//        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "/MercuryDemo/pic/");
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        } else {
            return getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, contentValues);
        }

    }

    private Uri getMediaUriFromPath(String path) {
        Uri mediaUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = getContentResolver().query(mediaUri, null, MediaStore.Images.Media.DISPLAY_NAME + "=?", new String[]{
                path.substring(path.lastIndexOf("/") + 1)
        }, null);
        Uri uri = null;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                uri = ContentUris.withAppendedId(mediaUri, cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Media._ID)));

            }
            cursor.close();
        }
        return uri;
    }

    public static File createNewFile(String path, boolean append) {
        File newFile = new File(path);
        if (!append) {
            if (newFile.exists()) {
                newFile.delete();
            } else {
                // 不存在，则删除带png后缀名的文件
                File prePngFile = new File(path + ".png");
                if (prePngFile.exists()) {
                    prePngFile.delete();
                }
            }
        }
        if (newFile.exists()) {
            newFile.delete();
        }
        if (!newFile.exists()) {
            try {
                File parent = newFile.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdirs();
                }
                newFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == RC_OPENCAMRA) {
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                uri = photoUri;
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", tempFile);
            } else {
                uri = Uri.fromFile(tempFile);
            }
            //发现有的手机 没有办法用uri转化，直接将uri转为string,glide可以加载,若是Q以下根据tempfile获取路径

            String path = ImageUtil.getRealPathFromUri(this, uri);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q && path == null) {
                path = tempFile.getAbsolutePath();
            } else if (path == null) {
                path = uri.toString();
            }
            Toast.makeText(this,path,Toast.LENGTH_SHORT).show();
            savaPath(path);
            Glide.with(this).load(uri).apply(RequestOptions.bitmapTransform(new CircleCrop())).placeholder(R.drawable.u).into(mAvatar);
        } else if (resultCode == RESULT_OK && requestCode == RC_OPENABLUM) {
            String path = ImageUtil.getRealPathFromUri(this, data.getData());
            savaPath(path);
            Glide.with(this).load(data.getData()).apply(RequestOptions.bitmapTransform(new CircleCrop())).placeholder(R.drawable.u).into(mAvatar);
        }
    }

    private void openAblum() {
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        } else {
            intent = new Intent(Intent.ACTION_PICK);
        }
        intent.setType("image/*");
        startActivityForResult(intent, RC_OPENABLUM);
    }

    private void test() {
        File imageFile = getExternalFilesDir("image");
        if (!imageFile.exists()) {
            imageFile.mkdir();
        }

        ContentResolver contentResolver = getContentResolver();
        try {
            ParcelFileDescriptor prcelFileDescriptor = contentResolver.openFileDescriptor(photoUri, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(data.getData()), null, options);?

//        Uri uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,cur)
    }

    /**
     * 通过绝对路径去获取uri
     *
     * @param path
     * @return
     */
    private Uri getImageContentUri(String path) {
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=?",
                new String[]{path}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            //1
            Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, String.valueOf(id));
            //2
            cursor.close();
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (new File(path).exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, path);
                assert cursor != null;
                cursor.close();
                return getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                assert cursor != null;
                cursor.close();
                return null;
            }
        }
    }

    /**
     * 根据uri 获取图片的bitmap
     *
     * @param uri
     * @return
     */

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void dealPic(String path, Intent data) {


//

    }


}