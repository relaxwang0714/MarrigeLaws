package com.project.android.marrigelaws;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.FileNotFoundException;

import static android.app.Activity.RESULT_OK;

/**
 * Created by 13zqn on 2017/5/16.
 */

public class Menu_msg_frg extends Fragment implements View.OnClickListener{
    private EditText mEditText;
    private ImageButton mImageButtonWrite;
    private ImageButton mImageButtonSet;
    private ImageView mImageView;
    private Button takePhoto;
    private Button selectPhoto;
    private Uri imageUri;
    int flag;
    private RelativeLayout mHistory;
    private RelativeLayout mCollection;
    private RelativeLayout mQuestion;
    private static final String TAG = "MainActivity";

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int SELECT_PHOTO = 3;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_my,container,false);

        mEditText = (EditText)v.findViewById(R.id.menu_my_name_set_edit);
        mQuestion = (RelativeLayout)v.findViewById(R.id.menu_my_question);
        mHistory = (RelativeLayout)v.findViewById(R.id.menu_my_history);
        mCollection = (RelativeLayout)v.findViewById(R.id.menu_my_collection);
        mImageButtonWrite = (ImageButton)v.findViewById(R.id.menu_my_write);
        mImageButtonSet = (ImageButton)v.findViewById(R.id.menu_my_set);
        mImageView = (ImageView)v.findViewById(R.id.circle_picture);

//        int[] pic = new int[] {mImageView.getId()};
//        Intent send = new Intent(getActivity(), Smart_Robot.class);
//        send.putExtra("sendResDrawable", pic);
//        startActivity(send);

        mQuestion.setOnClickListener(this);
        mHistory.setOnClickListener(this);
        mCollection.setOnClickListener(this);
        mImageButtonWrite.setOnClickListener(this);
        mImageButtonSet.setOnClickListener(this);
        mImageView.setOnClickListener(this);

        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_my_question:
                Intent intent = new Intent();
                intent.setClass(getActivity(),LawyerServe.class);
                getActivity().startActivity(intent);
                break;
            case R.id.menu_my_history:
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(),FindHistoryFragment.class);
                getActivity().startActivity(intent1);
                break;
            case R.id.menu_my_collection:
                Intent intent2 = new Intent();
                intent2.setClass(getActivity(),FindCollectionFragment.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.menu_my_set:
                Intent intent3 = new Intent();
                intent3.setClass(getActivity(),Menu_msg_frg_Set.class);
                startActivity(intent3);
                break;
            case R.id.menu_my_write:
                if(flag == 0){
                    mEditText.setEnabled(true);
                    flag = 1;
                }else {
                    if (mEditText.getText().length() > 5){
                        Toast.makeText(getActivity(),"不能超过五个字！",Toast.LENGTH_SHORT).show();
//                        mEditText.setEnabled(true);
                    }else if (mEditText.getText().length() == 0){
                        Toast.makeText(getActivity(),"用户名不能为空！",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        mEditText.setEnabled(false);
                        flag = 0;
                    }
                }
                break;
            case R.id.circle_picture:
                Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
                View view = LayoutInflater.from(getActivity()).inflate(R.layout.photo_dialog, null);
                takePhoto = (Button)view.findViewById(R.id.take_photo);
                selectPhoto = (Button) view.findViewById(R.id.select_photo);
                selectPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent("android.intent.action.PICK");
                        intent.setType("image/*");
                        intent.putExtra("crop",true);
                        intent.putExtra("scale",true);
//                        //设置宽高比例
//
//                        intent.putExtra("aspectX", 1);
//                        intent.putExtra("aspectY", 1);
//                        //设置裁剪图片宽高、
//                        intent.putExtra("outputX", 450);
//                        intent.putExtra("outputY", 450);
                        startActivityForResult(intent,SELECT_PHOTO);
                    }
                });
                takePhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //执行拍照前，应该先判断SD卡是否存在
                        String SDState = Environment.getExternalStorageState();
                        if(SDState.equals(Environment.MEDIA_MOUNTED))
                        {

                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//"android.media.action.IMAGE_CAPTURE"
                            /***
                             * 需要说明一下，以下操作使用照相机拍照，拍照后的图片会存放在相册中的
                             * 这里使用的这种方式有一个好处就是获取的图片是拍照后的原图
                             * 如果不实用ContentValues存放照片路径的话，拍照后获取的图片为缩略图不清晰
                             */
                            ContentValues values = new ContentValues();
                            imageUri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
                            /**-----------------*/
                            startActivityForResult(intent, TAKE_PHOTO);
                        }else{
                            Toast.makeText(getActivity(),"内存卡不存在", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //将布局设置给Dialog
                dialog.setContentView(view);
                //获取当前Activity所在的窗体
                Window dialogWindow = dialog.getWindow();
                //设置Dialog从窗体底部弹出
                dialogWindow.setGravity( Gravity.BOTTOM);
                //获得窗体的属性
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.y = 20;//设置Dialog距离底部的距离
//       将属性设置给窗体
                dialogWindow.setAttributes(lp);
                dialog.show();//显示对话框
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CROP_PHOTO);
                }
                break;
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(imageUri));
                        mImageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case SELECT_PHOTO:
                if (resultCode ==RESULT_OK) {
                    imageUri = data.getData();
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri,"image/*");
                    intent.putExtra("scale",true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                    startActivityForResult(intent,CROP_PHOTO);


                }
//                if (resultCode == RESULT_OK) {
//                    if (Build.VERSION.SDK_INT >=19) {
//                        handleImageOnKitKat(data);
//                    } else {
//                        handleImageBeforeKitKat(data);
//                    }
//                }
                break;
            default:break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath =null;
        Uri uri = data.getData();
        //
        Log.i("info",uri.toString());
        if (DocumentsContract.isDocumentUri(getActivity(),uri)) {
            //如果是document类型的Uri，则通过document id处理
            Log.i("info","DocumentsContract");
            String documentId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                Log.i("info","documents");

                String id = documentId.split(":")[1];//解析出数字格式的ID，split()是分割函数
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);

            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Log.i("info","downloads");

                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri,null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果不是document类型的Uri，则用不同方式处理
            Log.i("info","content");
            imagePath = getImagePath(uri,null);
        }
        Log.i("info","content-------");
        imagePath = getImagePath(uri,null);

//        Log.i("info",imagePath);
        displayImage(imagePath);//根据图片路径显示图片

    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        //通过Uri和selection来获取图片的真实路径
        Cursor cursor = getActivity().getContentResolver().query(externalContentUri,null,selection,null,null);
        if (cursor!=null) {
            while(cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();

        }
        return path;
    }



    private void displayImage(String imagePath) {
        if (imagePath != null) {
            try{
                Log.i("info",imagePath);
                Uri uri = Uri.parse(imagePath);
                Bitmap bitmap = BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                mImageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "获取图片失败！", Toast.LENGTH_SHORT).show();
        }
    }

}