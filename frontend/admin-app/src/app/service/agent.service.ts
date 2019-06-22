import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })

  export class AgentService 
  {
      constructor(private http: HttpClient){}

      addAgent(agentDTO)
      {
        return this.http.post('auth-service/agents/', agentDTO);
      }

      checkIfUsernameIsValid(username)
      {
        return this.http.get('auth-service/agents/' + username, {observe: 'response'});
      }

  }