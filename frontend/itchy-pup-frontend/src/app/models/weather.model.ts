export interface PollenData {
  dailyInfo: {
    date: string;
    pollenIndex: {
      grass: string;
      tree: string;
      weed: string;
    };
    pollenCount: {
      grass: number;
      tree: number;
      weed: number;
    };
  }[];
} 