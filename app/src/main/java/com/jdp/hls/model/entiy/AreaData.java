package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 6:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaData {

    private String Version;
    private AreaBean Area;

    public String getVersion() {
        return Version;
    }

    public void setVersion(String Version) {
        this.Version = Version;
    }

    public AreaBean getArea() {
        return Area;
    }

    public void setArea(AreaBean Area) {
        this.Area = Area;
    }

    public static class AreaBean {
        private List<ProvinceInfo> Nodes;

        public List<ProvinceInfo> getNodes() {
            return Nodes;
        }

        public void setNodes(List<ProvinceInfo> Nodes) {
            this.Nodes = Nodes;
        }

        public static class ProvinceInfo {

            private int RegionId;
            private String RegionName;
            private List<CityInfo> Nodes;

            public int getRegionId() {
                return RegionId;
            }

            public void setRegionId(int RegionId) {
                this.RegionId = RegionId;
            }

            public String getRegionName() {
                return RegionName;
            }

            public void setRegionName(String RegionName) {
                this.RegionName = RegionName;
            }

            public List<CityInfo> getNodes() {
                return Nodes;
            }

            public void setNodes(List<CityInfo> Nodes) {
                this.Nodes = Nodes;
            }

            public static class CityInfo {

                private int RegionId;
                private String RegionName;
                private List<AreaInfo> Nodes;

                public int getRegionId() {
                    return RegionId;
                }

                public void setRegionId(int RegionId) {
                    this.RegionId = RegionId;
                }

                public String getRegionName() {
                    return RegionName;
                }

                public void setRegionName(String RegionName) {
                    this.RegionName = RegionName;
                }

                public List<AreaInfo> getNodes() {
                    return Nodes;
                }

                public void setNodes(List<AreaInfo> Nodes) {
                    this.Nodes = Nodes;
                }

                public static class AreaInfo {
                    private int RegionId;
                    private String RegionName;
                    private List<StreetInfo> Nodes;

                    public int getRegionId() {
                        return RegionId;
                    }

                    public void setRegionId(int RegionId) {
                        this.RegionId = RegionId;
                    }

                    public String getRegionName() {
                        return RegionName;
                    }

                    public void setRegionName(String RegionName) {
                        this.RegionName = RegionName;
                    }

                    public List<?> getNodes() {
                        return Nodes;
                    }

                    public void setNodes(List<StreetInfo> Nodes) {
                        this.Nodes = Nodes;
                    }

                    public static class StreetInfo {
                        private int RegionId;
                        private String RegionName;

                        public int getRegionId() {
                            return RegionId;
                        }

                        public void setRegionId(int RegionId) {
                            this.RegionId = RegionId;
                        }

                        public String getRegionName() {
                            return RegionName;
                        }

                        public void setRegionName(String RegionName) {
                            this.RegionName = RegionName;
                        }
                    }
                }
            }
        }
    }
}
