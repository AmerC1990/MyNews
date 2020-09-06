package com.amercosovic.mynews

import android.content.Context
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import kotlinx.android.synthetic.main.activity_notifications.*
import kotlinx.android.synthetic.main.activity_search.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.mockito.Mockito.mock

class SearchQueryHelper {
    @Test fun `build Query For Notification Returns Correct Query With One Checkbox Category`()  {
        assert(com.amercosovic.mynews.util.buildQueryForNotification(searchQuery = "America",categories = listOf("Politics"))
            .equals("America&fq=Politics"))
    }
    @Test fun `build Query For Notification Returns Correct Query With Two Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForNotification(searchQuery = "America",categories = listOf("Politics","Travel"))
            .equals("America&fq=Politics&fq=Travel"))
    }
    @Test fun `build Query For Notification Returns Correct Query With Three Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForNotification(searchQuery = "America",categories = listOf("Politics","Travel","Business"))
            .equals("America&fq=Politics&fq=Travel&fq=Business"))
    }
    @Test fun `build Query For Notification Returns Correct Query With Four Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForNotification(searchQuery = "America",categories = listOf("Politics","Travel","Business","Sports"))
            .equals("America&fq=Politics&fq=Travel&fq=Business&fq=Sports"))
    }
    @Test fun `build Query For Notification Returns Correct Query With Five Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForNotification(searchQuery = "America",categories = listOf("Politics","Travel","Business","Sports","Entrepreneurs"))
            .equals("America&fq=Politics&fq=Travel&fq=Business&fq=Sports&fq=Entrepreneurs"))
    }
    @Test fun `build Query For Notification Returns Correct Query With Six Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForNotification(searchQuery = "America",categories = listOf("Politics","Travel","Business","Sports","Entrepreneurs","Arts"))
            .equals("America&fq=Politics&fq=Travel&fq=Business&fq=Sports&fq=Entrepreneurs&fq=Arts"))
    }
    @Test fun `build Query For Search Result Returns Correct Query With Begin Date And One Checkbox Category`()  {
        assert(com.amercosovic.mynews.util.buildQueryForSearchResult(searchQuery = "America",categories = listOf("Politics"),enterBeginDate = "03/09/2020",enterEndDate = "")
            .equals("America&begin_date=20200903&fq=Politics"))
    }
    @Test fun `build Query For Search Result Returns Correct Query With End Date And One Checkbox Category`()  {
        assert(com.amercosovic.mynews.util.buildQueryForSearchResult(searchQuery = "America",categories = listOf("Politics"),enterBeginDate = "",enterEndDate = "03/09/2020")
            .equals("America&end_date=20200903&fq=Politics"))
    }
    @Test fun `build Query For Search Result Returns Correct Query With Begin Date And End Date And One Checkbox Category`()  {
        assert(com.amercosovic.mynews.util.buildQueryForSearchResult(searchQuery = "America",categories = listOf("Politics"),enterBeginDate = "03/09/2020",enterEndDate = "03/09/2020")
            .equals("America&begin_date=20200903&end_date=20200903&fq=Politics"))
    }
    @Test fun `build Query For Search Result Returns Correct Query With Begin Date And Three Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForSearchResult(searchQuery = "America",categories = listOf("Politics","Travel","Arts"),enterBeginDate = "03/09/2020",enterEndDate = "")
            .equals("America&begin_date=20200903&fq=Politics&fq=Travel&fq=Arts"))
    }
    @Test fun `build Query For Search Result Returns Correct Query With Begin Date And End Date And Six Checkbox Categories`()  {
        assert(com.amercosovic.mynews.util.buildQueryForSearchResult(searchQuery = "America",categories = listOf("Politics","Travel","Arts","Business","Sports","Entrepreneurs"),enterBeginDate = "03/08/2018",enterEndDate = "01/09/2020")
            .equals("America&begin_date=20180803&end_date=20200901&fq=Politics&fq=Travel&fq=Arts&fq=Business&fq=Sports&fq=Entrepreneurs"))
    }
}
