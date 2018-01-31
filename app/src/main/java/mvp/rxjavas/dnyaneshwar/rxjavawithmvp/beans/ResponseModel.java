package mvp.rxjavas.dnyaneshwar.rxjavawithmvp.beans;

import java.util.List;

/**
 * Created by Dnyaneshwar Dalvi on 30/01/18.
 */
public class ResponseModel {


    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<Datum> data = null;

    /**
     * Gets page.
     *
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets page.
     *
     * @param page the page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Gets per page.
     *
     * @return the per page
     */
    public Integer getPer_page() {
        return per_page;
    }

    /**
     * Sets per page.
     *
     * @param per_page the per page
     */
    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * Gets total pages.
     *
     * @return the total pages
     */
    public Integer getTotal_pages() {
        return total_pages;
    }

    /**
     * Sets total pages.
     *
     * @param total_pages the total pages
     */
    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    /**
     * The type Datum.
     */
    public class Datum {

        private Integer id;
        private String first_name;
        private String last_name;
        private String avatar;

        /**
         * Gets id.
         *
         * @return the id
         */
        public Integer getId() {
            return id;
        }

        /**
         * Sets id.
         *
         * @param id the id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         * Gets first name.
         *
         * @return the first name
         */
        public String getFirst_name() {
            return first_name;
        }

        /**
         * Sets first name.
         *
         * @param first_name the first name
         */
        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        /**
         * Gets last name.
         *
         * @return the last name
         */
        public String getLast_name() {
            return last_name;
        }

        /**
         * Sets last name.
         *
         * @param last_name the last name
         */
        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        /**
         * Gets avatar.
         *
         * @return the avatar
         */
        public String getAvatar() {
            return avatar;
        }

        /**
         * Sets avatar.
         *
         * @param avatar the avatar
         */
        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
