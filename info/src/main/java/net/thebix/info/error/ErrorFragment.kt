package net.thebix.info.error

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.TextView
import net.thebix.info.R

class ErrorFragment : Fragment() {

    companion object {

        fun newInstance(title: String? = null, subtitle: String? = null, info: String? = null) = ErrorFragment()
            .apply {
                this.title = title ?: ""
                this.subtitle = subtitle ?: ""
                this.info = info ?: ""
            }
    }

    private val titleView get() = view?.findViewById(R.id.fragment_error_title) as TextView?
    private val subtitleView get() = view?.findViewById(R.id.fragment_error_subtitle) as TextView?
    private val infoView get() = view?.findViewById(R.id.fragment_error_info) as TextView?

    // TODO: properly store the state
    private var title: String = ""
    private var subtitle: String = ""
    private var info: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (title.isBlank() && subtitle.isBlank() && info.isBlank()) {
            return
        }

        if (title.isNotBlank()) {
            titleView?.text = title
        } else {
            titleView?.visibility = GONE
        }

        if (subtitle.isBlank()) {
            subtitleView?.visibility = GONE
        } else {
            subtitleView?.text = subtitle
        }

        if (info.isBlank()) {
            infoView?.visibility = GONE
        } else {
            infoView?.text = info
        }
    }

}
