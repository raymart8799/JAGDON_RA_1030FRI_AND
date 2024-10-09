package com.jagdon.newsapp

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction

class   MainActivity : AppCompatActivity(), HeadlineListFragment.OnHeadlineClickListener {

    private var isDualPane = false
    private var newsContentFragment: NewsContentFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Check if we're in dual-pane mode (landscape)
        isDualPane = findViewById<FrameLayout>(R.id.news_content_fragment_container) != null

        // If we're in portrait mode (single fragment), add HeadlineListFragment to fragment_container
        if (findViewById<FrameLayout>(R.id.fragment_container) != null) {
            if (savedInstanceState != null) return

            val headlineListFragment = HeadlineListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, headlineListFragment)
                .commit()
        }

        // If we're in dual-pane mode (landscape, two fragments)
        if (isDualPane) {
            // Load the NewsContentFragment in the second container (right side)
            newsContentFragment = NewsContentFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.news_content_fragment_container, newsContentFragment!!)
                .commit()
        }
    }

    override fun onHeadlineSelected(position: Int) {
        // Define the content for each headline
        val news = when (position) {
            0 -> "Content for Headline 1\n" +
                    "Quiboloy lawyers to pursue bid for hospital or house arrest\n" +
                    "MANILA — Kingdom of Jesus Christ leader Apollo Quiboloy's legal team is seeking home or hospital arrest for him as the Pasig Regional Trial Court prepares to hear a qualified human trafficking case against him.\n" +
                    "\n" +
                    "\"It's pending now, we are pursuing it. And as a matter of fact, we will be filing a supplemental motion to that effect to show that there is an urgent need for him to be, either in a hospital arrest or in a house arrest,\" KOJC legal counsel Israelito Torreon said.\n" +
                    "\n" +
                    "The lawyer said Quiboloy has a doctor checking on him daily, adding the controversial pastor \"is hopeful that the proceedings will be finished so that he can obtain his liberty again.\"\n" +
                    "\n" +
                    "Torreon declined to provide details on Quiboloy's medical condition."
            1 -> "Content for Headline 2\n" +
                    "AFP chief wants US missiles in Philippines ‘forever’\n" +
                    "MANILA – Armed Forces of the Philippines (AFP) Chief of Staff Romeo Brawner Jr. on Wednesday said he wants the United States’ Typhon midrange capability missile in the Philippines “forever’” as part of the country’s defense. \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "The US brought the missile system to the Philippines in April for the joint military exercises between American and Filipino troops. There is still no timeline for how long it will remain in the Philippines.\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "“Hindi ko rin alam kung ano ‘yong plano eh pero kung ako ang masusunod (I don't know what the plan is either, but if it were up to me), if I were given a choice, I would like to have the Typhons here in the Philippines forever dahil kailangan natin ‘yan para sa depensa natin,” he told reporters in a chance interview during the 5th Asian Defense, Security, and Crisis Management Exhibition and Conference (ADAS 2024) at the World Trade Center.\n" +
                    "\n" +
                    "He said the Philippine government has expressed its desire for the missile system to stay in the country, but declined to disclose the US’ response. \n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "The country’s top military official said the Philippines also wanted to buy its own missile system, but would work first on “the other things that we need in order for us to be able to support our platforms.”\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "China's Ministry of Foreign Affairs have been calling for the withdrawal of the missile system from the Philippines, saying it undermines the peace and stability in the region.\n" +
                    "\n"
            2 -> "Content for Headline 3\n" +
                    "'Hindi ako aalis dito': Sara Duterte rejects resignation calls from lawmakers\n" +
                    "MANILA — Vice President Sara Duterte on Wednesday said she is not going anywhere after some lawmakers suggested that she resign for her refusal to personally defend the 2025 budget of her office. \n" +
                    "\n" +
                    "• VP Duterte can step down if she is no longer interested in performing her duties: solon\n" +
                    "\n" +
                    "Duterte was responding to remarks by Ako Bicol party-list Rep. Raul Angelo Bongalon that she seems uninterested in \"her duties and functions\" as Vice President and can be asked to quit. There has been no formal call for her to resign.\n" +
                    "\n" +
                    "\"Hindi ako sasagot sa 'Young Guns' dahil kailangan ko sumagot sa 32 million na bumoto sa akin. Hindi sa isa o dalawang tao. Hindi ako aalis dito dahil nilagay ako ng mga tao dito believing I will work for the country,\" Duterte said in a press conference.\n" +
                    "\n" +
                    "(I will not respond to the 'Young Guns' because I answer to 32 million who voted for me and not to just one or two people. I am not stepping down because the people put me here believeing I will work for the country)\n" +
                    "\n" +
                    "The 'Young Guns' is a group of House members who are 40 years old and below, with some of them on their first terms as representatives.\n" +
                    "\n" +
                    "The House of Representatives rescheduled the plenary deliberations on the Office of the Vice President's budget hoping Duterte would show up."
            else -> "Content for Headline 4\n" +
                    "Shiela Guo detained at NBI facility\n" +
                    "MANILA — Shiela Guo has been taken into custody by the National Bureau of Investigation (NBI) after she was fetched by the operatives in the Senate Tuesday afternoon. \n" +
                    "\n" +
                    "• Senate panel frees Shiela Guo, transfers custody to immigration bureau\n" +
                    "\n" +
                    "She was processed in the NBI headquarters and was eventually transferred to the NBI detention facility in the Bilibid compound in Muntinlupa.\n" +
                    "\n" +
                    "The NBI Organized and Transnational Crimes Division explained that while the Senate placed her under the legal custody of the Bureau of Immigration, the physical custody of Guo is under the NBI.\n" +
                    "\n" +
                    "• Shiela Guo admits Alice is not actually her 'sister'\n" +
                    "\n" +
                    "Guo earlier testified in the Senate along with \"sister\" Alice Guo in the ongoing investigation of Pogo operations in Tarlac."
        }

        if (isDualPane) {
            // In dual-pane mode (landscape), directly update the content in the second fragment
            newsContentFragment?.updateContent(news)
        } else {
            // In single-pane mode (portrait), replace with NewsContentFragment in the same container
            val contentFragment = NewsContentFragment().apply {
                arguments = Bundle().apply {
                    putString("news", news)
                }
            }

            // Replace the fragment in portrait mode (single-pane)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, contentFragment)
                .addToBackStack(null)  // Add to back stack to allow back navigation
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        }
    }
}
