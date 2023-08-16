import React from 'react'
import Header from '../components/Header'
import { useState } from 'react'
import chatRequest from '../fetch/ChatFetch'

const LandingPage = () => {

    const [apikey, setApiKey] = useState<string>("")
    const [loading, setLoading] =  useState<boolean>(false)

    const handleSubmit = () => {
        setLoading(true)
        localStorage.setItem("apikey", apikey)
        chatRequest("what is api?").then((res) => {
            window.location.href = "/chat"
            setLoading(false)
        }).catch((err) => {
            localStorage.clear();
            console.log(err);
            alert("The api key is invalid")
            setLoading(false)
        })
    }

    return (
        <div>
            <Header></Header>
            <div className='md:text-center text-gray-700 font-medium text-lg px-2 md:px-4 py-2 md:py-4'>
                <p>The Chat JSON  is made to talk to ChatGPT and get back a response in valid JSON format. </p>
                <p>If you need JSON responses, the backend checks if the response is already in JSON or tries to convert it. </p>
                <p>If needed, the backend makes repeated attempts to communicate with ChatGPT to ensure a JSON response format.</p>
            </div>

            <div className='flex md:justify-center px-2 md:px-4 py-4'>
                <input className='bg-gray-200  md:w-[50%] px-2 md:px-4 rounded-xl py-2 font-medium '
                    type="text" placeholder='Enter Your ChatGpt API key'
                    onChange={(e) => setApiKey(e.target.value)} value={apikey} />
                <button className='ml-2 bg-blue-500 text-white  px-4 py-2 rounded-xl 
                hover:bg-blue-400' onClick={handleSubmit} >{ loading ? "Processing..." : "Submit" }</button>
            </div>

            <div>
                <div className='text-center font-medium text-lg'>
                    To get Started you need openai api key and the api key is free. You can get it from <a className='text-blue-500' href='https://platform.openai.com/account/api-keys'>here</a> <br />
                    if you stuck refer these <a className='text-blue-500' href='https://www.maisieai.com/help/how-to-get-an-openai-api-key-for-chatgpt'>guide</a>
                </div>
            </div>
        </div>
    )
}

export default LandingPage