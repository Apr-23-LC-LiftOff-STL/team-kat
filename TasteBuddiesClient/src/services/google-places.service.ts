import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class GooglePlacesService {

  private apiKey = 'AIzaSyDTDdRCcrr3w5o6H_DdVHS7twjKKuAljeQ'; //Enter your API key here
  
  async getNearbyPlaces(latLng: string, radius: string, keyword: string): Promise<any> {

    const url = `https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${latLng}&radius=${radius}&type=restaurant&keyword=${keyword}&key=${this.apiKey}`;
    let proxy = "https://cors-anywhere.herokuapp.com/";  //Activate by visiting cors-anywhere.herokuapp.com/corsdemo
    let proxyUrl =proxy+ url; //uses the proxy combined with the regular url to bypass the CORS Restrictions.

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
