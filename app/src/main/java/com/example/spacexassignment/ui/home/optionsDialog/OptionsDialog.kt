package com.example.spacexassignment.ui.home.optionsDialog

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.spacexassignment.common.base.ui.BaseBottomSheetDialog
import com.example.spacexassignment.databinding.DialogOptionsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class OptionsDialog : BaseBottomSheetDialog<DialogOptionsBinding>() {
    override val _bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> DialogOptionsBinding
        get() = DialogOptionsBinding::inflate
    private val args: OptionsDialogArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.links?.videoLink?.let { Log.d("hassanein",it) }
        args.links?.youtubeId?.let { Log.d("hassanein",it) }
        binding.apply {
            args.links?.articleLink?.let { link->tvArticle.setOnClickListener { navigateToLink(link) } }
            args.links?.wikipedia?.let {link-> tvWikipedia.setOnClickListener { navigateToLink(link) } }
            args.links?.youtubeId?.let {link-> tvVideo.setOnClickListener { navigateToYoutube(link) } }
        }

    }

    private fun navigateToLink(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        requireActivity().startActivity(intent)
    }

    private fun navigateToYoutube(link: String) {
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + link))
        val intentBrowser =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$link"))
        try {
            requireActivity().startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            requireActivity().startActivity(intentBrowser)
        }
    }

}


