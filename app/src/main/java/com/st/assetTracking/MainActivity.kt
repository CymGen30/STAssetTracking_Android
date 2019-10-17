package com.st.assetTracking

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.st.assetTracking.blueTile.BlueTileMainActivity
import com.st.assetTracking.sigfox.SigfoxTrackerConfig


class MainActivity : AppCompatActivity(), SupportedPackageAdapter.PackageListener{

    companion object{

        private fun startSigfoxAssetTrackingActivity(context: Context){
            val intent = Intent(context,SigfoxTrackerConfig::class.java)
            context.startActivity(intent)
        }

        private fun startBlueTileActivity(context: Context){
            val intent = Intent(context, BlueTileMainActivity::class.java)
            context.startActivity(intent)
        }

        private fun startSmarTagApp(context: Context){
            val packageName = "com.st.smartTag"
            var intent = context.packageManager.getLaunchIntentForPackage(packageName)
            if (intent == null) {
                // Bring user to the market or let them choose an app?
                intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("market://details?id=$packageName")
                }
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }

        private val SUPPORTED_PACKAGES:Array<SupportedPackage> = arrayOf(
            SupportedPackage(name = R.string.package_sifgox_name,
                    description = R.string.package_sifgox_desc,
                    image = R.drawable.sigfox,
                    moreInfo = Uri.parse("https://www.st.com/content/st_com/en/products/embedded-software/mcu-mpu-embedded-software/stm32-embedded-software/stm32-ode-function-pack-sw/fp-atr-sigfox1.html"),
                    onSelect = ::startSigfoxAssetTrackingActivity),
            SupportedPackage(name = R.string.package_smarTag_name,
                description = R.string.package_smarTag_desc,
                image = R.drawable.sigfox,
                moreInfo = Uri.parse("https://www.st.com/en/embedded-software/fp-sns-smartag1.html"),
                onSelect = ::startSmarTagApp),
            SupportedPackage(name = R.string.package_bluetile_name,
                description = R.string.package_bluetile_desc,
                image = R.drawable.sigfox,
                moreInfo = Uri.parse("https://www.st.com/"),
                onSelect = ::startBlueTileActivity)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = SupportedPackageAdapter(SUPPORTED_PACKAGES,this)
        findViewById<RecyclerView>(R.id.main_package_list).adapter = adapter
    }

    override fun onPackageSelected(item: SupportedPackage) {
        item.onSelect(this)
    }

    override fun onMoreInfoSelected(item: SupportedPackage) {
        if (item.moreInfo == null)
            return
        val intent = Intent(Intent.ACTION_VIEW,item.moreInfo)
        startActivity(intent)
    }

}
