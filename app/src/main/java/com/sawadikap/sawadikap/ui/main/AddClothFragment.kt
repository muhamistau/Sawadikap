package com.sawadikap.sawadikap.ui.main


import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sawadikap.sawadikap.R
import com.sawadikap.sawadikap.data.remote.SawadikapRemote
import com.sawadikap.sawadikap.domain.model.request.ClothRequest
import com.sawadikap.sawadikap.domain.model.response.ClothResponse
import com.sawadikap.sawadikap.util.Constant
import kotlinx.android.synthetic.main.fragment_add_cloth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream

class AddClothFragment : Fragment(), View.OnClickListener {

    var imageUri: Uri? = null
    lateinit var storageFolder: StorageReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_cloth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        storageFolder = FirebaseStorage.getInstance().reference.child("user-image")

        addToWardrobeButton.setOnClickListener(this)
        fromCamera.setOnClickListener(this)
        fromGallery.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.addToWardrobeButton -> {
                Log.d("SELECTED_NAME", nameEditText.text.toString())
                Log.d(
                    "SELECTED_GENDER",
                    getSelectedText(genderChipGroup, genderChipGroup.checkedChipId)
                )
                Log.d("SELECTED_SIZE", getSelectedText(sizeChipGroup, sizeChipGroup.checkedChipId))
                Log.d("SELECTED_AGE", getSelectedText(ageChipGroup, ageChipGroup.checkedChipId))
                Log.d("SELECTED_RATING", ratingBar.rating.toString())
                addToWardrobe(
                    26,
                    getSelectedText(ageChipGroup, ageChipGroup.checkedChipId),
                    getSelectedText(sizeChipGroup, sizeChipGroup.checkedChipId),
                    getSelectedText(genderChipGroup, genderChipGroup.checkedChipId),
                    nameEditText.text.toString(),
                    ratingBar.rating.toString()
                )
            }
            R.id.fromCamera -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (activity?.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        && activity?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {
                        requestPermissions(
                            arrayOf(Manifest.permission.CAMERA),
                            Constant.IMAGE_CAPTURE_CODE
                        )
                        requestPermissions(
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            Constant.PERMISSION_CODE
                        )
                    } else {
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(
                            cameraIntent,
                            Constant.CAMERA_REQUEST
                        )
                    }
                } else {
                    openCamera()
                }
            }
            R.id.fromGallery -> {
                val photoPickerIntent = Intent(Intent.ACTION_PICK)
                photoPickerIntent.type = "image/*"
                startActivityForResult(
                    photoPickerIntent,
                    Constant.GALLERY_REQUEST_CODE
                )
            }
        }
    }

    private fun addToWardrobe(
        id: Int, age: String, size: String, gender: String,
        name: String, status: String
    ) {
        val sawadikapService = SawadikapRemote.create()
        val imageName =
            storageFolder.child("image${imageUri?.lastPathSegment}")

        var pictureUrl = ""
        imageUri?.let {
            imageName.putFile(it).addOnSuccessListener {
                imageName.downloadUrl.addOnSuccessListener { uri ->
                    pictureUrl = uri.toString()
                    val clothRequest = ClothRequest(id, pictureUrl, age, size, gender, name, status)
                    sawadikapService.addToWardrobe(clothRequest)
                        .enqueue(object : Callback<ClothResponse> {
                            override fun onFailure(call: Call<ClothResponse>, t: Throwable) {
                                activity?.runOnUiThread {
                                    Toast.makeText(activity, "GAGAL", Toast.LENGTH_SHORT).show()
                                }
                                Log.d("GAGAL", t.message.toString())
                            }

                            override fun onResponse(
                                call: Call<ClothResponse>,
                                response: Response<ClothResponse>
                            ) {
                                findNavController().navigateUp()
                            }
                        })
                }
            }.addOnFailureListener {
                activity?.runOnUiThread {
                    Toast.makeText(activity, "Koneksi anda bermasalah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getSelectedText(chipGroup: ChipGroup, id: Int): String {
        val mySelection = chipGroup.findViewById<Chip>(id)
        return mySelection?.text?.toString() ?: ""
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera")
        imageUri = activity?.contentResolver
            ?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(
            cameraIntent,
            Constant.IMAGE_CAPTURE_CODE
        )
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        Uri file = Uri.fromFile(getOutputMediaFile());
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
//
//        startActivityForResult(intent, IMAGE_CAPTURE_CODE);
    }

    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver, inImage,
            "Title", null
        )
        return Uri.parse(path)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                Constant.GALLERY_REQUEST_CODE -> {
                    imageUri = data?.data

                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            activity?.contentResolver,
                            imageUri
                        )
                        activity?.runOnUiThread {
                            cameraImage.setImageBitmap(bitmap)
                            button.visibility = View.GONE
                        }
                    } catch (e: IOException) {
                        Log.i("TAG", "Some exception $e")
                    }
                }

                Constant.CAMERA_REQUEST -> {
                    val photo = data!!.extras!!["data"] as Bitmap?

                    imageUri = photo?.let { getImageUri(activity as Context, it) }

                    var imageStream: InputStream? = null
                    try {
                        imageStream = imageUri?.let {
                            activity?.contentResolver?.openInputStream(
                                it
                            )
                        }
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bmp = BitmapFactory.decodeStream(imageStream)

                    var stream: ByteArrayOutputStream? = ByteArrayOutputStream()
                    bmp.compress(Bitmap.CompressFormat.JPEG, 70, stream)
                    cameraImage.setImageBitmap(bmp)

                    try {
                        stream?.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                    activity?.runOnUiThread {
                        cameraImage.setImageBitmap(photo)
                        button.visibility = View.GONE
                    }
                }
            }
        }
    }
}
