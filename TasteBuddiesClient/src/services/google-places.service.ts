import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class GooglePlacesService {

  private apiKey = '';
  
  async getNearbyPlaces(latLng: string, radius: string, keyword: string): Promise<any> {

    const url = `https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${latLng}&radius=${radius}&type=restaurant&keyword=${keyword}&key=${this.apiKey}`;
    let proxy = "https://cors-anywhere.herokuapp.com/";
    let proxyUrl =proxy+ url;

    try {
      const response = await axios.get(proxyUrl);
      const data = response.data;
      console.log(data);
      return data;
    } catch (error) {
      console.error('Error fetching nearby places:', error);
      throw error;
    }

  }
  
}
