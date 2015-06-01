//old verify login
/*@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST, consumes = "application/json")
     public String verifyLogin(@RequestBody String data) {
     System.out.println("sssssssssssssssss ");
     Gson gson = new Gson();
     UserDetails userLoging = gson.fromJson(data, UserDetails.class);
     //Persona aPerson = gson.fromJson(data, Persona.class);
     System.out.println(userLoging);
     return "";//json;
 }*/

//Authenticate USER
//idToken = userLogin.getIdtoken();
            //do login
            // if userLogin.id doesn't exist in DB, register the user
            // else get info from db
            // authenticate user
            /*UsernamePasswordAuthenticationToken authRequest
             = new UsernamePasswordAuthenticationToken("user", "password");
             // Authenticate the user
             Authentication authentication = authenticationManager.authenticate(authRequest);
             SecurityContext securityContext = SecurityContextHolder.getContext();
             securityContext.setAuthentication(authentication);

             // Create a new session and add the security context.
             HttpSession session = request.getSession(true);
             session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);*/