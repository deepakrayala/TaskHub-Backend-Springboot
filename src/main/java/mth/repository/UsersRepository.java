public Object signin(Map<String, Object> data)
{
    Map<String, Object> response = new HashMap<>();

    try
    {
        String email = data.get("username").toString();
        String password = data.get("password").toString();

        Users U = UR.findByEmail(email);

        if(U == null || !passwordEncoder.matches(password, U.getPassword()))
            throw new Exception("Invalid Credentials!");

        response.put("code", 200);
        response.put("message", "Validation Success");
        response.put("jwt", JWT.generateToken(U.getEmail(), U.getRole(), U.getId()));
    }
    catch(Exception e)
    {
        response.put("code", 500);
        response.put("message", e.getMessage());
    }

    return response;
}
