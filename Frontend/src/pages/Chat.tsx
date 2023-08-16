import React from 'react'
import Header from '../components/Header'
import { InputForm } from '../components/InputForm'
import { useState } from 'react'
import UserQsn from '../components/UserQsn'
import Response from '../components/Response'
import chatRequest from '../fetch/ChatFetch'
import Loader from '../components/Loader'

type conversation = {
    prompt: string,
    response: object
}

const Chat = () => {

    const [promptChange, setPromptChange] = useState<string>("");
    const [loading, setLoading] = useState<boolean>(false);
    const [prompt, setPrompt] = useState<string>("");
    const [response, setResponse] = useState({});

    const [chatHistory, setChatHistory] = useState<conversation[]>([]);

    const handleSubmit = () => {
        setResponse({});
        setLoading(true);
        setPrompt(promptChange);
        chatRequest(promptChange).then((res) => {
            setChatHistory([...chatHistory, { prompt: promptChange, response: res.response }]);
            setResponse(res.response);
            setPromptChange("");
            setLoading(false);
        }).catch((err) => {
            console.log(err);
            setLoading(false);
        })
    }


    return (
        <div className=' min-h-screen '>
            <Header />
            {
                chatHistory.map((item, index) => <>
                    <UserQsn prompt={item.prompt} loading={false} />
                    <Response response={item.response} />
                </>)
            }
            {loading && <Loader />}

            <InputForm setPrompt={setPromptChange} prompt={promptChange} handleSubmit={handleSubmit} />

        </div>
    )
}

export default Chat