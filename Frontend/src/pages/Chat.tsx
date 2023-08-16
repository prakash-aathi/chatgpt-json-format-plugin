import React from 'react'
import Header from '../components/Header'
import { InputForm } from '../components/InputForm'
import { useState } from 'react'
import UserQsn from '../components/UserQsn'
import Response from '../components/Response'
import chatRequest from '../fetch/ChatFetch'
import Loader from '../components/Loader'

const Chat = () => {

    const [promptChange, setPromptChange] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);
    const [prompt, setPrompt] = useState<string>("");
    const [response, setResponse] = useState({});

    const handleSubmit = () => {
        setResponse({});
        setLoading(true);
        setPrompt(promptChange);
        setPromptChange("");
        console.log(promptChange);
        chatRequest(promptChange).then((res) => {
            console.log(res);
            console.log(res.response);
            setResponse(res.response);
            setLoading(false);
        }).catch((err) => {
            console.log(err);
            setLoading(false);
        })
    }


    return (
        <div className=' min-h-screen '>
            <Header />
            <UserQsn prompt={prompt} loading={loading} />
            {loading && <Loader />}
            <Response response={response} />
            <InputForm setPrompt={setPromptChange} prompt={promptChange} handleSubmit={handleSubmit} />

        </div>
    )
}

export default Chat